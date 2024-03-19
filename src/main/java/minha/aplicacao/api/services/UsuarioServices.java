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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioServices implements UserDetailsService {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;
    public UsuarioServices(){
    }
    public Usuario setUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            Usuario usuario = new Usuario(usuarioCreateDTO);
            usuario.setSenha(bCryptPasswordEncoder.encode(usuarioCreateDTO.senha())); //Encapsulate
            iUsuarioRepository.save(usuario);
            return usuario;
        } catch (DataIntegrityViolationException e) {
            throw new UserDuplicateDataException();
        }
    }
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuario = (ArrayList<Usuario>) iUsuarioRepository.findAll();
        if(usuario.isEmpty()) throw new UsersNotFoundException();
        return usuario;
    }
    public Usuario getUsuarioPorId(Integer usuarioId){

        Optional<Usuario> usuario = iUsuarioRepository.findById(usuarioId);
        if(usuario.isEmpty()) throw new UserNotFoundException();
        return usuario.get();
    }
    public Usuario updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = getUsuarioPorId(usuarioUpdateDTO.idUsuario());
        try{
            usuario.updateUsuario(usuarioUpdateDTO);
            iUsuarioRepository.saveAndFlush(usuario);
            return usuario;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    public Usuario deleteLogicalUsuario(Integer idUsuario){
        Usuario usuario = getUsuarioPorId(idUsuario);
        try{
            usuario.deleteUsuario();
            iUsuarioRepository.save(usuario);
            return usuario;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return iUsuarioRepository.findByEmail(email);
    }



}
