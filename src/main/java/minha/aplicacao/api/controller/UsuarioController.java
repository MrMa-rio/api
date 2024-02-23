package minha.aplicacao.api.controller;

import minha.aplicacao.api.DTO.UsuarioDTO;
import minha.aplicacao.api.models.Usuario;
import minha.aplicacao.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping
    public void cadastrar(@RequestBody UsuarioDTO usuarioDTO){
        usuarioRepository.save(new Usuario(usuarioDTO));
    }
    public void modificar(@RequestBody UsuarioDTO usuarioDTO) {
    }

}
