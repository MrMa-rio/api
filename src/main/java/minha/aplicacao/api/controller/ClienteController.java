package minha.aplicacao.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.Cliente.ClienteCreateDTO;
import minha.aplicacao.api.DTO.Cliente.ClienteUpdateDTO;
import minha.aplicacao.api.exceptions.clientExceptions.ClientNotFoundException;
import minha.aplicacao.api.exceptions.clientExceptions.ClientsNotFoundException;
import minha.aplicacao.api.models.Cliente.Cliente;
import minha.aplicacao.api.responseBody.ResponseBody;
import minha.aplicacao.api.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"clientes", "clientes/"})

public class ClienteController {
    @Autowired
    private ClienteServices clienteServices;
    @PostMapping
    public ResponseEntity<?> setCliente(@RequestBody @Valid ClienteCreateDTO clienteCreateDTO) {
        try {
            return ResponseEntity.ok(clienteServices.setCliente(clienteCreateDTO));
        }catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }
    @GetMapping
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
    public ResponseEntity<?> updateCliente(@RequestBody @Valid ClienteUpdateDTO clienteUpdateDTO) {
        try{
            return ResponseEntity.ok(clienteServices.updateCliente(clienteUpdateDTO));
        }catch (ClientNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        } // AJUDA NA CRIACAO DA EXCECAO POIS CASO PASSADO UM VALOR QUE N ESTA SENDO ESPERADO A APLICACAO LANCA UM ERRO
    }
    @DeleteMapping("/{idCliente}")
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
            ResponseBody responseBody = new ResponseBody(400, "CLIENTE INVALIDO!!");
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
}
