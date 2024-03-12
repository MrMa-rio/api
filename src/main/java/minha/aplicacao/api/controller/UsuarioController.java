package minha.aplicacao.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.UsuarioCreateDTO;
import minha.aplicacao.api.DTO.UsuarioUpdateDTO;
import minha.aplicacao.api.responseBody.ResponseBody;
import minha.aplicacao.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;
    @PostMapping
    @Transactional
    public ResponseEntity<String> setUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws JsonProcessingException { //Futuramente encapsular para q que nao precise inserir um throws direto
        try {
            return ResponseEntity.ok(usuarioServices.setUsuario(usuarioCreateDTO));
        }catch (Exception e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.ok(responseBody.toJson());
        }
    }
    @GetMapping
    public ResponseEntity<String> getTodosUsuarios(){
        try {
            return ResponseEntity.ok(usuarioServices.getUsuarios());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity<String> getUsuarioPorId(@PathVariable String idUsuario) throws JsonProcessingException {
        try {
            String usuario = usuarioServices.getUsuarioPorId(Integer.valueOf(idUsuario));
            return ResponseEntity.ok(usuario);
        } catch (EntityNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, "Usuario nao encontrado");
            return ResponseEntity.ok(responseBody.toJson());
        }
        catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "Usuario Invalido!!");
            return ResponseEntity.ok(responseBody.toJson());
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<String> updateUsuario(@RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) throws JsonProcessingException {

        try{
            return ResponseEntity.ok(usuarioServices.updateUsuario(usuarioUpdateDTO));
        }catch (EntityNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, "Nao foi possivel encontrar este usuario!");
            return ResponseEntity.ok(responseBody.toJson());
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> deleteLogicalUsuario(@PathVariable String idUsuario) throws JsonProcessingException {
        try {
            String usuario = usuarioServices.deleteLogicalUsuario(Integer.valueOf(idUsuario));
            return ResponseEntity.ok(usuario);
        } catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "Usuario Invalido!!");
            return ResponseEntity.ok(responseBody.toJson());
        }
        catch (EntityNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, "Usuario nao encontrado");
            return ResponseEntity.ok(responseBody.toJson());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
