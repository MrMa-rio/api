package minha.aplicacao.api.services;

import minha.aplicacao.api.DTO.Usuario.UsuarioCreateDTO;
import minha.aplicacao.api.DTO.Usuario.UsuarioUpdateDTO;
import minha.aplicacao.api.exceptions.userExceptions.UserDuplicateDataException;
import minha.aplicacao.api.exceptions.userExceptions.UserNotFoundException;
import minha.aplicacao.api.exceptions.userExceptions.UsersNotFoundException;
import minha.aplicacao.api.models.Usuario.Usuario;
import minha.aplicacao.api.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        } catch (DataIntegrityViolationException e) {
            throw new UserDuplicateDataException();
        }
    }
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuario = (ArrayList<Usuario>) IUsuarioRepository.findAll();
        if(usuario.isEmpty()) throw new UsersNotFoundException();
        return usuario;
    }
    public Usuario getUsuarioPorId(Integer usuarioId){

        Optional<Usuario> usuario = IUsuarioRepository.findById(usuarioId);
        if(usuario.isEmpty()) throw new UserNotFoundException();
        return usuario.get();
    }
    public Usuario updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = getUsuarioPorId(usuarioUpdateDTO.idUsuario());
        try{
            usuario.updateUsuario(usuarioUpdateDTO);
            IUsuarioRepository.saveAndFlush(usuario);
            return usuario;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    public Usuario deleteLogicalUsuario(Integer idUsuario){
        Usuario usuario = getUsuarioPorId(idUsuario);
        try{
            usuario.deleteUsuario();
            IUsuarioRepository.saveAndFlush(usuario);
            return usuario;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
