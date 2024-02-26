package minha.aplicacao.api.controller;

import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.UsuarioDTO;
import minha.aplicacao.api.models.Usuario;
import minha.aplicacao.api.repository.UsuarioRepository;
import minha.aplicacao.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;
    @PostMapping
    public String cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO){
        return usuarioServices.setUsuario(usuarioDTO);
    }
    public void modificar(@RequestBody UsuarioDTO usuarioDTO) {

    }

}
