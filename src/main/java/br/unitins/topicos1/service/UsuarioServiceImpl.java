package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.usuario.UpdateEmailDTO;
import br.unitins.topicos1.dto.usuario.UpdateNomeDTO;
import br.unitins.topicos1.dto.usuario.UpdateSenhaDTO;
import br.unitins.topicos1.dto.usuario.UpdateTelefoneDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) {

       if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setLogin(dto.login());

        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));

        novoUsuario.setPerfil(Perfil.USER);

        if (dto.listaTelefone() != null && 
                    !dto.listaTelefone().isEmpty()){
            novoUsuario.setListaTelefone(new ArrayList<Telefone>());
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                novoUsuario.getListaTelefone().add(telefone);
            }
        }

        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateADMIN(Long id) {
        Usuario usuario = repository.findById(id);
    
        usuario.setPerfil(Perfil.ADMIN);
        
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateUSER(Long id) {
        Usuario usuario = repository.findById(id);
    
        usuario.setPerfil(Perfil.USER);
        
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Usuario usuario = repository.findById(id);
        usuario.setNomeImagem(nomeImagem);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
             return null;
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = repository.findByLoginAndSenha(login, senha);
        if (usuario == null) 
            throw new ValidationException("login", "Login ou senha inválido");
        
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        Usuario usuario = repository.findByLogin(login);
        if (usuario == null) 
            throw new ValidationException("login", "Login inválido");
        
        return UsuarioResponseDTO.valueOf(usuario);
    }
    
    @Override
    @Transactional
    public UsuarioResponseDTO updateNome(@Valid UpdateNomeDTO dto, String login) {

        Usuario usuario = repository.findByLogin(login);

        // Pedindo a senha do usuario como medida de proteção
        if (usuario.getSenha().equals(hashService.getHashSenha(dto.senhaAtual()))) {
            usuario.setNome(dto.nome());

        } else
            throw new ValidationException("Senha", "Senha atual incorreta. ");

        return UsuarioResponseDTO.valueOf(usuario);

    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateSenha(@Valid UpdateSenhaDTO dto, String login) {

        Usuario usuario = repository.findByLogin(login);

        // Pedindo a senha do usuario como medida de proteção
        if (usuario.getSenha().equals(hashService.getHashSenha(dto.senhaAtual()))) {
            usuario.setSenha(hashService.getHashSenha(dto.novaSenha()));

        } else
            throw new ValidationException("Senha", "Senha atual incorreta.");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateEmail(@Valid UpdateEmailDTO dto, String login) {

        Usuario usuario = repository.findByLogin(login);

        // Pedindo a senha do usuario como medida de proteção
        if (usuario.getSenha().equals(hashService.getHashSenha(dto.senhaAtual()))) {
            usuario.setEmail(dto.email());

        } else
            throw new ValidationException("Senha", "Senha atual incorreta. ");

        return UsuarioResponseDTO.valueOf(usuario);

    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateTelefone(@Valid UpdateTelefoneDTO dto, String login) {

        Usuario usuario = repository.findByLogin(login);

        usuario.getListaTelefone().clear();

        List<Telefone> telefones = usuario.getListaTelefone();

        if (usuario.getSenha().equals(hashService.getHashSenha(dto.senhaAtual()))) {
            usuario.setListaTelefone(new ArrayList<Telefone>());
            for (TelefoneDTO tel : dto.listaTelefones()) {
                Telefone telefone = new Telefone();

                telefone.setCodigoArea(tel.codigoArea());
                telefone.setNumero(tel.numero());

                telefones.add(telefone);

                usuario.setListaTelefone(telefones);

            }

        } else
            throw new ValidationException("Senha", "Senha atual incorreta. ");

        return UsuarioResponseDTO.valueOf(usuario);
    }

}
