package minha.aplicacao.api.services;

import minha.aplicacao.api.DTO.Pedido.PedidoCreateDTO;
import minha.aplicacao.api.DTO.Pedido.PedidoUpdateDTO;
import minha.aplicacao.api.exceptions.orderExceptions.OrderNotFoundException;
import minha.aplicacao.api.exceptions.orderExceptions.OrdersNotFoundException;
import minha.aplicacao.api.models.Pedido.Pedido;
import minha.aplicacao.api.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if(pedido.isEmpty()) throw new OrderNotFoundException();
        return pedido.get();
    }

    public ArrayList<Pedido> getPedidos(){
        ArrayList<Pedido> pedidos = (ArrayList<Pedido>) iPedidoRepository.findAll();
        if (pedidos.isEmpty()) throw new OrdersNotFoundException();
        return pedidos;
    }

    public ArrayList<Pedido> getPedidosPorIdCliente(Integer idCliente){
        ArrayList<Pedido> pedidos = iPedidoRepository.findByFkCliente(idCliente);
        if (pedidos.isEmpty()) throw new OrdersNotFoundException("NÃO EXISTE PEDIDOS PARA ESTE CLIENTE");
        return pedidos;
    }
    public Pedido updatePedido(PedidoUpdateDTO pedidoUpdateDTO){
        Pedido pedido = getPedidoPorId(pedidoUpdateDTO.idPedido());
        pedido.updatePedido(pedidoUpdateDTO.statusPedido());
        iPedidoRepository.saveAndFlush(pedido);
        return pedido;
    }
    public Pedido cancelPedido(Integer idPedido){
        Pedido pedido = getPedidoPorId(idPedido);
        pedido.cancelPedido();
        iPedidoRepository.saveAndFlush(pedido);
        return pedido;
    }
}
