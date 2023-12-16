package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.itemP.ItemPedidoDTO;
import br.unitins.topicos1.dto.pedido.PedidoDTO;
import br.unitins.topicos1.dto.pedido.PedidoResponseDTO;
import br.unitins.topicos1.model.Tenis;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.FormaPagamento;
import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.StatusPedido;
import br.unitins.topicos1.repository.TenisRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    TenisRepository tenisRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO insert(String login, @Valid PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setDataCompra(LocalDateTime.now());
        Usuario usuario = usuarioRepository.findByLogin(login);

        pedido.setUsuario(usuario);

        // calculo do total do pedido
        Double total = 0.0;
        for (ItemPedidoDTO itemDto : dto.itens()) {
            total += (itemDto.preco() * itemDto.quantidade());
        }
        pedido.setTotalPedido(total);

        pedido.setPagamento(FormaPagamento.ValueOf(dto.pagamento()));
        pedido.setStatusPedido(StatusPedido.AGUARDANDOPAG);

        // adicionando os itens do pedido
        pedido.setItens(new ArrayList<ItemPedido>());
        for (ItemPedidoDTO itemDto : dto.itens()) {
            ItemPedido item = new ItemPedido();
            item.setPreco(itemDto.preco());
            item.setQuantidade(itemDto.quantidade());
            item.setPedido(pedido);
            Tenis tenis = tenisRepository.findById(itemDto.idProduto());
            item.setTenis(tenis);

            // atualizado o estoque
            tenis.setEstoque(tenis.getEstoque() - item.getQuantidade());

            pedido.getItens().add(item);
        }

        // buscando o usuario pelo login
        pedido.setUsuario(usuarioRepository.findByLogin(login));

        // salvando no banco
        pedidoRepository.persist(pedido);

        // atualizando o estoque
  

        return PedidoResponseDTO.valueOf(pedido);
        
    }

    @Override
    public List<PedidoResponseDTO> findByUsuario(Long idUsuario) {
        List<Pedido> list = pedidoRepository.findByUsuario(idUsuario);
        return list.stream().map(compra -> PedidoResponseDTO.valueOf(compra)).collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    // @Override
    // public List<PedidoResponseDTO> findByAll() {
    //     return pedidoRepository.listAll().stream()
    //         .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    // }

    @Override
    public List<PedidoResponseDTO> findByAll() {
        return pedidoRepository.listAll().stream()
            .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }
    
    @Override
    @Transactional
    public PedidoResponseDTO updateStatusPAGO(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
    
        pedido.setStatusPedido(StatusPedido.PAGO);
        
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO updateStatusCANCELADO(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
    
        pedido.setStatusPedido(StatusPedido.CANCELADO);
        
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO updateStatusENVIADO(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
    
        pedido.setStatusPedido(StatusPedido.ENVIADO);
        
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO updateStatusFINALIZADO(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
    
        pedido.setStatusPedido(StatusPedido.FINALIZADO);
        
        return PedidoResponseDTO.valueOf(pedido);
    }

}
