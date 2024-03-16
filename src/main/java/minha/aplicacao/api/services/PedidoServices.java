package minha.aplicacao.api.services;

import minha.aplicacao.api.DTO.Pedido.PedidoCreateDTO;
import minha.aplicacao.api.DTO.Pedido.PedidoUpdateDTO;
import minha.aplicacao.api.models.Pedido;
import minha.aplicacao.api.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoServices {

    @Autowired
    IPedidoRepository iPedidoRepository;
    public Pedido setPedido(PedidoCreateDTO pedidoCreateDTO){
        Pedido pedido = new Pedido(pedidoCreateDTO);
        iPedidoRepository.save(pedido);
        return pedido;
    }
    public Pedido getPedidoPorId(Integer idPedido){
        Optional<Pedido> pedido = iPedidoRepository.findById(idPedido);
        if(pedido.isEmpty())  return null;
        return pedido.get();
    }

    public void getPedidosPorIdCliente(Integer idCliente){
        //TODO
    }
    public void updatePedido(PedidoUpdateDTO pedidoUpdateDTO){
        //TODO
    }
    public void deletePedido(Integer idPedido){
        //TODO
    }
}
