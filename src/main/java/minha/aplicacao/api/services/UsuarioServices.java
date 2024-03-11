package minha.aplicacao.api.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import minha.aplicacao.api.DTO.UsuarioDTO;
import minha.aplicacao.api.models.Usuario;
import minha.aplicacao.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;

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
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    public String cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario(usuarioDTO);
        try {
            usuarioRepository.save(usuario);
            return usuario.toJson();
        }catch (DataIntegrityViolationException | JsonProcessingException e){
           System.out.print(e.getMessage());
           return e.getMessage();
        }
    }
    public String todosUsuarios(){
        List<Usuario> usuario = usuarioRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
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
