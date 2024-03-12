package minha.aplicacao.api.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityNotFoundException;
import minha.aplicacao.api.DTO.UsuarioCreateDTO;
import minha.aplicacao.api.DTO.UsuarioUpdateDTO;
import minha.aplicacao.api.models.Usuario;
import minha.aplicacao.api.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UsuarioServices {
    @Autowired
    private IUsuarioRepository IUsuarioRepository;
    public UsuarioServices(){
    }
    public String setUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        try {
            Usuario usuario = new Usuario(usuarioCreateDTO);
            IUsuarioRepository.save(usuario);
            return usuario.toJson();
        } catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getUsuarios(){
        ArrayList<Usuario> usuario = (ArrayList<Usuario>) IUsuarioRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.writeValueAsString(usuario);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getUsuarioPorId(Integer usuarioId){

        try {
            Usuario usuario = IUsuarioRepository.getReferenceById(usuarioId);
            return usuario.toJson();
        }
        catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO) {
        String existeUsuario = getUsuarioPorId(usuarioUpdateDTO.idUsuario());
        if(existeUsuario.isEmpty()){
            return null;
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Usuario usuario = objectMapper.readValue(existeUsuario, Usuario.class);
            usuario.updateUsuario(usuarioUpdateDTO);
            IUsuarioRepository.save(usuario);
            return usuario.toJson();
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
    public String deleteLogicalUsuario(Integer idUsuario){
        String existeUsuario = getUsuarioPorId(idUsuario);
        if(existeUsuario.isEmpty()){
            return null;
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Usuario usuario = objectMapper.readValue(existeUsuario, Usuario.class);
            usuario.deleteUsuario();
            IUsuarioRepository.save(usuario);
            return usuario.toJson();
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
