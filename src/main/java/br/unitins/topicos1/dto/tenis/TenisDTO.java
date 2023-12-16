package br.unitins.topicos1.dto.tenis;

import br.unitins.topicos1.model.CategoriaTenis;
import jakarta.validation.constraints.NotEmpty;

public record TenisDTO(
    @NotEmpty(message = "O campo marca não pode ser nulo.")
    String marca,

    @NotEmpty(message = "O campo modelo não pode ser nulo.")
    String modelo,

    @NotEmpty(message = "O campo categoria do tenis não pode ser nulo.")
    CategoriaTenis categoriaTenis,  

    Integer estoque,
    
    @NotEmpty(message = "O campo cor não pode ser nulo.")
    String cor,

    @NotEmpty(message = "O campo tamanho não pode ser nulo.")
    Integer tamanho,

    @NotEmpty(message = "O campo valor não pode ser nulo.")
    Double valor

) {
}
