package minha.aplicacao.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.ClienteCreateDTO;
import minha.aplicacao.api.DTO.ClienteUpdateDTO;
import minha.aplicacao.api.models.Cliente;
import minha.aplicacao.api.responseBody.ResponseBody;
import minha.aplicacao.api.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{idCliente}")
    public ResponseEntity<?> getClientePorId(@PathVariable String idCliente) {
        try {
            Cliente cliente = clienteServices.getClientePorId(Integer.valueOf(idCliente));
            return ResponseEntity.ok(cliente);
        } catch (NoSuchElementException e) {
            ResponseBody responseBody = new ResponseBody(404, "Cliente nao encontrado");
            return ResponseEntity.ok(responseBody);
        }
        catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "Cliente Invalido!!");
            return ResponseEntity.ok(responseBody);
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateCliente(@RequestBody @Valid ClienteUpdateDTO clienteUpdateDTO) {
        try{
            return ResponseEntity.ok(clienteServices.updateCliente(clienteUpdateDTO));
        }catch (NoSuchElementException e){
            ResponseBody responseBody = new ResponseBody(404, "Nao foi possivel encontrar este cliente!");
            return ResponseEntity.ok(responseBody);
        }
    }
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<?> deleteLogicalCliente(@PathVariable String idCliente) {
        try {
            Cliente cliente = clienteServices.deleteLogicalCliente(Integer.valueOf(idCliente));
            return ResponseEntity.ok(cliente);
        } catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "Cliente Invalido!!");
            return ResponseEntity.ok(responseBody);
        }
        catch (NoSuchElementException e) {
            ResponseBody responseBody = new ResponseBody(404, "Cliente nao encontrado");
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
