package minha.aplicacao.api.services;

import minha.aplicacao.api.DTO.UsuarioDTO;
import minha.aplicacao.api.models.Usuario;
import minha.aplicacao.api.repository.UsuarioRepository;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public UsuarioServices(){
    }
    public String setUsuario(UsuarioDTO usuarioDTO) {

        try {
            usuarioRepository.save(new Usuario(usuarioDTO));
            return usuarioDTO.toString();
        }catch (RuntimeException e){
            return e.getMessage();
        }


    }

}
