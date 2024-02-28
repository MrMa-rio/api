package minha.aplicacao.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.UsuarioDTO;
import minha.aplicacao.api.exceptions.StandardErrorDTO;
import minha.aplicacao.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController

@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {

        try {
            return ResponseEntity.ok(usuarioServices.cadastrar((usuarioDTO)));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    public void modificar(@RequestBody UsuarioDTO usuarioDTO) {

    }

}
