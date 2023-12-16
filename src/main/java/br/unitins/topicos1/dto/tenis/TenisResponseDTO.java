package br.unitins.topicos1.dto.tenis;

import br.unitins.topicos1.model.CategoriaTenis;
import br.unitins.topicos1.model.Tenis;

public record TenisResponseDTO(
    Long id,
    String marca,
    String modelo,
    CategoriaTenis categoriaTenis,
    Integer estoque,
    String cor,
    Integer tamanho,
    Double valor
) { 
    public static TenisResponseDTO valueOf(Tenis tenis){
        return new TenisResponseDTO(
            tenis.getId(),
            tenis.getMarca(),
            tenis.getModelo(),
            tenis.getCategoria(),
            tenis.getEstoque(),
            tenis.getCor(),
            tenis.getTamanho(),
            tenis.getValor());
    }
}
