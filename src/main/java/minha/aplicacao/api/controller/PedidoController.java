package minha.aplicacao.api.controller;

import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.Pedido.PedidoCreateDTO;
import minha.aplicacao.api.DTO.Pedido.PedidoUpdateDTO;
import minha.aplicacao.api.models.Pedido.Pedido;
import minha.aplicacao.api.services.PedidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"pedidos","pedidos/"})
public class PedidoController {

    @Autowired
    PedidoServices pedidoServices;

    @PostMapping
    public ResponseEntity<?> setPedido(@RequestBody @Valid PedidoCreateDTO pedidoCreateDTO){
        Pedido pedido = pedidoServices.setPedido(pedidoCreateDTO);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<?> getPedidoPorId(@PathVariable String idPedido){
        Pedido pedido = pedidoServices.getPedidoPorId(Integer.valueOf(idPedido));
        return ResponseEntity.ok(pedido);
    }
//    @GetMapping({"/clientes/{idCliente}/pedidos", "/clientes/{idCliente}/pedidos/"})
//    public void getPedidosPorIdCliente(@PathVariable String idCliente){
//        TODO
//    }
//    @GetMapping(
//        {"/clientes/{idCliente}/pedidos/{idPedido}"}
//    )
//    public void getPedidoPorIdCliente(@PathVariable(name = "idCliente") String idCliente, @PathVariable("idPedido") String idPedido){
//        TODO
//    } MOVER PARA CONTROLLER DO CLIENTE
    @PutMapping
    public ResponseEntity<?> updatePedido(@RequestBody @Valid PedidoUpdateDTO pedidoUpdateDTO){
        Pedido pedido = pedidoServices.updatePedido(pedidoUpdateDTO);
        return ResponseEntity.ok(pedido);
    }
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<?> cancelPedido(@PathVariable String idPedido){
        Pedido pedido  = pedidoServices.cancelPedido(Integer.valueOf(idPedido));
        return ResponseEntity.ok(pedido);
    }
}