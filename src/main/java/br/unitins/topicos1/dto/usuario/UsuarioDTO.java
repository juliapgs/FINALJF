package br.unitins.topicos1.dto.usuario;

import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO (
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "O CPF não pode estar em branco")
    String cpf,
    String email,
    @NotBlank(message = "O campo login não pode ser nulo.")
    String login,
    @NotBlank(message = "O campo senha não pode ser nulo.")
    String senha,
    List<TelefoneDTO> listaTelefone,
    Integer idPerfil
     
)
{

}
