package br.unitins.topicos1.dto.pedido;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.dto.itemP.ItemPedidoResponseDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.model.FormaPagamento;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.StatusPedido;

public record PedidoResponseDTO(
    Long id,
    LocalDateTime dataHora,
    UsuarioResponseDTO usuario,
    FormaPagamento pagamento,
    StatusPedido statusPedido,
    Double totalPedido,
    List<ItemPedidoResponseDTO> itens
) { 
    public static PedidoResponseDTO valueOf(Pedido pedido){
        return new PedidoResponseDTO(
            pedido.getId(), 
            pedido.getDataCompra(),
            UsuarioResponseDTO.valueOf(pedido.getUsuario()),
            pedido.getPagamento(),
            pedido.getStatusPedido(),
            pedido.getTotalPedido(),
            ItemPedidoResponseDTO.valueOf(pedido.getItens()));
    }
}
