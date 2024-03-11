package minha.aplicacao.api.controller;

import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.UsuarioDTO;
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
    public String cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO){
        return usuarioServices.setUsuario(usuarioDTO);
    }
    @GetMapping
    public ResponseEntity<String> todosUsuarios(){
        try {
            return ResponseEntity.ok(usuarioServices.todosUsuarios());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//    @GetMapping(value = "teste/{usuarioId}", params = "usuarioId")
//    public ResponseEntity<String> acharUsuarioPorId(@RequestParam("usuarioId") int usuarioId){
//        try {
//            return ResponseEntity.ok(usuarioServices.acharUsuarioPorId(usuarioId));
//        }
//        catch (Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
