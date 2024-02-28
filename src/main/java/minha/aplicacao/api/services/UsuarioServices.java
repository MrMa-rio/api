package minha.aplicacao.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import minha.aplicacao.api.DTO.UsuarioDTO;
import minha.aplicacao.api.exceptions.ExceptionsHandler;
import minha.aplicacao.api.models.Usuario;
import minha.aplicacao.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.sql.SQLSyntaxErrorException;

@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public UsuarioServices(){
    }

    public String cadastrar(UsuarioDTO usuarioDTO) throws JsonProcessingException {

        Usuario usuario = new Usuario(usuarioDTO);

        try {
            usuarioRepository.save(usuario);
            return usuario.toJson();
        }catch (DataIntegrityViolationException e){
            ExceptionsHandler exceptionsHandler = new ExceptionsHandler();
            return exceptionsHandler.dataConflictException(e);

        }

//        usuarioRepository.save(usuario);
//        return usuario.toJson();




    }

}
