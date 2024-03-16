package minha.aplicacao.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.Usuario.UsuarioCreateDTO;
import minha.aplicacao.api.DTO.Usuario.UsuarioUpdateDTO;
import minha.aplicacao.api.exceptions.userExceptions.UserNotFoundException;
import minha.aplicacao.api.exceptions.userExceptions.UsersNotFoundException;
import minha.aplicacao.api.models.Usuario;
import minha.aplicacao.api.responseBody.ResponseBody;
import minha.aplicacao.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        catch (UsersNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> getUsuarioPorId(@PathVariable String idUsuario) {
        try {
            Usuario usuario = usuarioServices.getUsuarioPorId(Integer.valueOf(idUsuario));
            return ResponseEntity.ok(usuario);
        } catch (UserNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "USUARIO INVALIDO!!");
            return ResponseEntity.ok(responseBody);
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateUsuario(@RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {

        try{
            return ResponseEntity.ok(usuarioServices.updateUsuario(usuarioUpdateDTO));
        }catch (UserNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> deleteLogicalUsuario(@PathVariable String idUsuario) {
        try {
            Usuario usuario = usuarioServices.deleteLogicalUsuario(Integer.valueOf(idUsuario));
            return ResponseEntity.ok(usuario);
        } catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "USUARIO INVALIDO!!");
            return ResponseEntity.ok(responseBody);
        }catch (UserNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }

    }
}
