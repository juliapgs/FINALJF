package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.tenis.TenisDTO;
import br.unitins.topicos1.dto.tenis.TenisResponseDTO;
import br.unitins.topicos1.model.Tenis;
import br.unitins.topicos1.repository.TenisRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TenisServiceImpl implements TenisService {

    @Inject
    TenisRepository repository;

    @Override
    @Transactional
    public TenisResponseDTO insert(TenisDTO dto) {
        Tenis novoTenis = new Tenis();
            novoTenis.setMarca(dto.marca());
            novoTenis.setModelo(dto.modelo());
            novoTenis.setCategoria(dto.categoriaTenis());
            novoTenis.setEstoque(dto.estoque());
            novoTenis.setCor(dto.cor());
            novoTenis.setTamanho(dto.tamanho());
            novoTenis.setValor(dto.valor());
        
        repository.persist(novoTenis);

        return TenisResponseDTO.valueOf(novoTenis);
    }

    @Override
    @Transactional
    public TenisResponseDTO update(TenisDTO dto, Long id) {
        
        Tenis tenis = repository.findById(id);
        if (tenis!= null) {
            tenis.setMarca(dto.marca());
            tenis.setModelo(dto.modelo());
            tenis.setCategoria(dto.categoriaTenis());
            tenis.setEstoque(dto.estoque());
            tenis.setCor(dto.cor());
            tenis.setTamanho(dto.tamanho());
            tenis.setValor(dto.valor());
        } else 
            throw new NotFoundException();

        return TenisResponseDTO.valueOf(tenis);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) 
            throw new NotFoundException();
    }

    @Override
    public TenisResponseDTO findById(Long id) {
        return TenisResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<TenisResponseDTO> findByModelo(String modelo) {
        return repository.findByModelo(modelo).stream()
            .map(e -> TenisResponseDTO.valueOf(e)).toList();
   }

    @Override
    public List<TenisResponseDTO> findByMarca(String marca) {
        return repository.findByMarca(marca).stream()
            .map(e -> TenisResponseDTO.valueOf(e)).toList();
   }

    @Override
    public List<TenisResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> TenisResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public TenisResponseDTO updateImagem(Long id, String nomeImagem) {
        Tenis tenis = repository.findById(id);
        tenis.setNomeImagem(nomeImagem);
        return TenisResponseDTO.valueOf(tenis);
    }
    
}
