package minha.aplicacao.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.ClienteCreateDTO;
import minha.aplicacao.api.DTO.ClienteUpdateDTO;
import minha.aplicacao.api.responseBody.ResponseBody;
import minha.aplicacao.api.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping({"clientes", "clientes/"})
public class ClienteController {
    @Autowired
    private ClienteServices clienteServices;
    @PostMapping

    public ResponseEntity setCliente(@RequestBody @Valid ClienteCreateDTO clienteCreateDTO) {
        try {
            return ResponseEntity.ok(clienteServices.setCliente(clienteCreateDTO));
        }catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }
    @GetMapping
    public ResponseEntity getTodosClientes(){
        try {
            return ResponseEntity.ok(clienteServices.getClientes());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{idCliente}")
    public ResponseEntity<String> getUsuarioPorId(@PathVariable String idCliente) throws JsonProcessingException {
        try {
            String cliente = clienteServices.getClientePorId(Integer.valueOf(idCliente));
            return ResponseEntity.ok(cliente);
        } catch (EntityNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, "Cliente nao encontrado");
            return ResponseEntity.ok(responseBody.toJson());
        }
        catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "Cliente Invalido!!");
            return ResponseEntity.ok(responseBody.toJson());
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<String> updateCliente(@RequestBody @Valid ClienteUpdateDTO clienteUpdateDTO) throws JsonProcessingException {

        try{
            return ResponseEntity.ok(clienteServices.updateCliente(clienteUpdateDTO));
        }catch (EntityNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, "Nao foi possivel encontrar este cliente!");
            return ResponseEntity.ok(responseBody.toJson());
        }
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<String> deleteLogicalCliente(@PathVariable String idCliente) throws JsonProcessingException {
        try {
            String cliente = clienteServices.deleteLogicalCliente(Integer.valueOf(idCliente));
            return ResponseEntity.ok(cliente);
        } catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "Cliente Invalido!!");
            return ResponseEntity.ok(responseBody.toJson());
        }
        catch (EntityNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, "Cliente nao encontrado");
            return ResponseEntity.ok(responseBody.toJson());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
