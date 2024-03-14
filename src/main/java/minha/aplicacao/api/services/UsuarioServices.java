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
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioServices {
    @Autowired
    private IUsuarioRepository IUsuarioRepository;
    public UsuarioServices(){
    }
    public Usuario setUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        try {
            Usuario usuario = new Usuario(usuarioCreateDTO);
            IUsuarioRepository.save(usuario);
            return usuario;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuario = (ArrayList<Usuario>) IUsuarioRepository.findAll();
        try {
            return usuario;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public Usuario getUsuarioPorId(Integer usuarioId){

        try {
            Optional<Usuario> usuario = IUsuarioRepository.findById(usuarioId);
            return usuario.orElseThrow();
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException(e);
        }
    }
    public Usuario updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = getUsuarioPorId(usuarioUpdateDTO.idUsuario());
        if(usuario == null ) return null;
        try{
            usuario.updateUsuario(usuarioUpdateDTO);
            IUsuarioRepository.save(usuario);
            return usuario;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    public Usuario deleteLogicalUsuario(Integer idUsuario){
        Usuario usuario = getUsuarioPorId(idUsuario);
        if(usuario == null) return null;
        try{
            usuario.deleteUsuario();
            IUsuarioRepository.save(usuario);
            return usuario;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
