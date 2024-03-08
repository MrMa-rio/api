package minha.aplicacao.api.services;

<<<<<<< Updated upstream
=======
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
>>>>>>> Stashed changes
import minha.aplicacao.api.DTO.UsuarioDTO;
import minha.aplicacao.api.models.Usuario;
import minha.aplicacao.api.repository.UsuarioRepository;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
import org.springframework.stereotype.Service;

import java.sql.SQLException;
=======
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
>>>>>>> Stashed changes

@NoArgsConstructor
@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;
<<<<<<< Updated upstream
    public UsuarioServices(){
    }
    public String setUsuario(UsuarioDTO usuarioDTO) {

        try {
            usuarioRepository.save(new Usuario(usuarioDTO));
            return usuarioDTO.toString();
        }catch (RuntimeException e){
            return e.getMessage();
        }


=======


    public String cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario(usuarioDTO);
        try {
            usuarioRepository.save(usuario);
            return usuario.toJson();
        }catch (DataIntegrityViolationException | JsonProcessingException e){
           System.out.print(e.getMessage());
           return e.getMessage();
        }
>>>>>>> Stashed changes
    }
    public String todosUsuarios(){
        List<Usuario> usuario = usuarioRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(usuario);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String acharUsuarioPorId(int usuarioId){

        try {
            Object teste = usuarioRepository.findById(usuarioId);

            Usuario usuario  = new Usuario( (UsuarioDTO) teste);
            return usuario.toJson();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
