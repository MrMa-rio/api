package minha.aplicacao.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.Usuario.UsuarioCommonDTO;
import minha.aplicacao.api.DTO.Usuario.UsuarioCreateDTO;
import minha.aplicacao.api.DTO.Usuario.UsuarioUpdateDTO;
import minha.aplicacao.api.exceptions.userExceptions.UserNotFoundException;
import minha.aplicacao.api.exceptions.userExceptions.UsersNotFoundException;
import minha.aplicacao.api.models.Usuario.Usuario;
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
    @Operation(summary = "Cadastra um novo usuario")
    public ResponseEntity<?> setUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = usuarioServices.setUsuario(usuarioCreateDTO);

        UsuarioCommonDTO usuarioCommonDTO = new UsuarioCommonDTO(usuario);
        try {
            return ResponseEntity.ok(usuarioCommonDTO);
        }catch (Exception e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }
    @GetMapping
    @Operation(summary = "Todos os usuarios")
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
    @Operation(summary = "Pega um usuario através do ID")
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
    @Operation(summary = "Atualiza os dados de um usuario")
    public ResponseEntity<?> updateUsuario(@RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {

        try{
            return ResponseEntity.ok(usuarioServices.updateUsuario(usuarioUpdateDTO));
        } catch (UserNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{idUsuario}")
    @Operation(summary = "Inativa o cadastro de um usuario")
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
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
