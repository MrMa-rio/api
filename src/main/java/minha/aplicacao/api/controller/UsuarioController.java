package minha.aplicacao.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.UsuarioCreateDTO;
import minha.aplicacao.api.DTO.UsuarioUpdateDTO;
import minha.aplicacao.api.models.Usuario;
import minha.aplicacao.api.responseBody.ResponseBody;
import minha.aplicacao.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController

@RequestMapping({"usuarios", "usuarios/"})
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;
    @PostMapping
    public ResponseEntity<?> setUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) {
        try {
            return ResponseEntity.ok(usuarioServices.setUsuario(usuarioCreateDTO));
        }catch (Exception e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }
    @GetMapping
    public ResponseEntity<?> getTodosUsuarios(){
        try {
            return ResponseEntity.ok(usuarioServices.getUsuarios());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> getUsuarioPorId(@PathVariable String idUsuario) {
        try {
            Usuario usuario = usuarioServices.getUsuarioPorId(Integer.valueOf(idUsuario));
            return ResponseEntity.ok(usuario);
        } catch (NoSuchElementException e) {
            ResponseBody responseBody = new ResponseBody(404, "Usuario nao encontrado");
            return ResponseEntity.ok(responseBody);
        }
        catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "Usuario Invalido!!");
            return ResponseEntity.ok(responseBody);
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateUsuario(@RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {

        try{
            return ResponseEntity.ok(usuarioServices.updateUsuario(usuarioUpdateDTO));
        }catch (NoSuchElementException e){
            ResponseBody responseBody = new ResponseBody(404, "Nao foi possivel encontrar este usuario!");
            return ResponseEntity.ok(responseBody);
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> deleteLogicalUsuario(@PathVariable String idUsuario) {
        try {
            Usuario usuario = usuarioServices.deleteLogicalUsuario(Integer.valueOf(idUsuario));
            return ResponseEntity.ok(usuario);
        } catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "Usuario Invalido!!");
            return ResponseEntity.ok(responseBody);
        }
        catch (NoSuchElementException e) {
            ResponseBody responseBody = new ResponseBody(404, "Usuario nao encontrado");
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
