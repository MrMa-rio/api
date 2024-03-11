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
import java.util.Optional;

@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public UsuarioServices(){
    }
    public String setUsuario(UsuarioDTO usuarioDTO) {

        try {
            Usuario usuario = new Usuario(usuarioDTO);
            usuarioRepository.save(usuario);
            return usuario.toJson();
        } catch (RuntimeException | JsonProcessingException e) {
            return e.getMessage();
        }
    }
    public String getUsuarios(){
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
            Optional<Usuario> usuarioDTO = usuarioRepository.findById(usuarioId);
            if (!usuarioDTO.isPresent()) {
                return null;
            }
            return usuarioDTO.get().toJson();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
