package minha.aplicacao.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.Pedido.PedidoCreateDTO;
import minha.aplicacao.api.DTO.Pedido.PedidoUpdateDTO;
import minha.aplicacao.api.exceptions.orderExceptions.OrderNotFoundException;
import minha.aplicacao.api.models.Pedido.Pedido;
import minha.aplicacao.api.responseBody.ResponseBody;
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
    @Operation(summary = "Gera um novo pedido para um cliente")
    public ResponseEntity<?> setPedido(@RequestBody @Valid PedidoCreateDTO pedidoCreateDTO){
        try{
            Pedido pedido = pedidoServices.setPedido(pedidoCreateDTO);
            return ResponseEntity.ok(pedido);
        }catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(500, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
    @GetMapping("/{idPedido}")
    @Operation(summary = "Pega um pedido através do ID")
    public ResponseEntity<?> getPedidoPorId(@PathVariable String idPedido){
        try{
            Pedido pedido = pedidoServices.getPedidoPorId(Integer.valueOf(idPedido));
            return ResponseEntity.ok(pedido);
        } catch ( OrderNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(500, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
    @PutMapping
    public ResponseEntity<?> updatePedido(@RequestBody @Valid PedidoUpdateDTO pedidoUpdateDTO){
        try{
            Pedido pedido = pedidoServices.updatePedido(pedidoUpdateDTO);
            return ResponseEntity.ok(pedido);
        } catch ( OrderNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(500, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<?> cancelPedido(@PathVariable String idPedido){
        try{
            Pedido pedido  = pedidoServices.cancelPedido(Integer.valueOf(idPedido));
            return ResponseEntity.ok(pedido);
        }
         catch ( OrderNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(500, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
    @GetMapping("/{idPedido}/calculo")
    public void calculoPedido(){

    }
}