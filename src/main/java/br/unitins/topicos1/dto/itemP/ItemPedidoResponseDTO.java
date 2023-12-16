package br.unitins.topicos1.dto.itemP;

import java.util.List;

import br.unitins.topicos1.model.ItemPedido;

public record ItemPedidoResponseDTO(
    Integer quantidade,
    Double preco,
    Long idProduto,
    String modelo
) { 
    public static ItemPedidoResponseDTO valueOf(ItemPedido item){
        return new ItemPedidoResponseDTO(
            item.getQuantidade(), 
            item.getPreco(),
            item.getTenis().getId(),
            item.getTenis().getModelo());
    }

    public static List<ItemPedidoResponseDTO> valueOf(List<ItemPedido> item) {
       return item.stream().map(i -> ItemPedidoResponseDTO.valueOf(i)).toList();
    }

}
