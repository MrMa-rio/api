package minha.aplicacao.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
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
    @Transactional
    public String cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO){
        return usuarioServices.setUsuario(usuarioDTO);
    }
    @GetMapping
    public ResponseEntity<String> todosUsuarios(){
        try {
            return ResponseEntity.ok(usuarioServices.getUsuarios());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity<String> acharUsuarioPorId(@PathVariable int idUsuario){
        try {
            return ResponseEntity.ok(usuarioServices.acharUsuarioPorId(idUsuario));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
