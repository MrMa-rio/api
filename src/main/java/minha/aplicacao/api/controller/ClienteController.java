package minha.aplicacao.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.Cliente.ClienteCreateDTO;
import minha.aplicacao.api.DTO.Cliente.ClienteUpdateDTO;
import minha.aplicacao.api.exceptions.clientExceptions.ClientNotFoundException;
import minha.aplicacao.api.exceptions.clientExceptions.ClientsNotFoundException;
import minha.aplicacao.api.models.Cliente.Cliente;
import minha.aplicacao.api.models.Pedido.Pedido;
import minha.aplicacao.api.responseBody.ResponseBody;
import minha.aplicacao.api.services.ClienteServices;
import minha.aplicacao.api.services.PedidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping({"clientes", "clientes/"})

public class ClienteController {
    @Autowired
    private ClienteServices clienteServices;
    @Autowired
    private PedidoServices pedidoServices;

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente")
    public ResponseEntity<?> setCliente(@RequestBody @Valid ClienteCreateDTO clienteCreateDTO) {
        try {
            return ResponseEntity.ok(clienteServices.setCliente(clienteCreateDTO));
        }catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }
    @GetMapping
    @Operation(summary = "Pega todos os clientes")
    public ResponseEntity<?> getTodosClientes(){
        try {
            return ResponseEntity.ok(clienteServices.getClientes());
        }
        catch (ClientsNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
    @GetMapping("/{idCliente}")
    @Operation(summary = "Pega um cliente através do ID")
    public ResponseEntity<?> getClientePorId(@PathVariable String idCliente) {
        try {
            Cliente cliente = clienteServices.getClientePorId(Integer.valueOf(idCliente));
            return ResponseEntity.ok(cliente);
        } catch (ClientNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "CLIENTE INVALIDO!!");
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
    @PutMapping
    @Transactional
    @Operation(summary = "Atualiza os dados de um cliente")
    public ResponseEntity<?> updateCliente(@RequestBody @Valid ClienteUpdateDTO clienteUpdateDTO) {
        try{
            return ResponseEntity.ok(clienteServices.updateCliente(clienteUpdateDTO));
        }catch (ClientNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }
    @DeleteMapping("/{idCliente}")
    @Operation(summary = "Inativa o cadastro de um cliente")
    public ResponseEntity<?> deleteLogicalCliente(@PathVariable String idCliente) {
        try {
            Cliente cliente = clienteServices.deleteLogicalCliente(Integer.valueOf(idCliente));
            return ResponseEntity.ok(cliente);
        } catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "CLIENTE INVALIDO!!");
            return ResponseEntity.ok(responseBody);
        }
        catch (ClientNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
    @GetMapping({"/{idCliente}/pedidos", "/{idCliente}/pedidos/"})
    @Operation(summary = "Pega os pedidos de um cliente através do ID")
    public ResponseEntity<?> getPedidosPorIdCliente(@PathVariable String idCliente){
       ArrayList<Pedido> pedidos = pedidoServices.getPedidosPorIdCliente(Integer.valueOf(idCliente));
       return ResponseEntity.ok(pedidos);
    }
}
