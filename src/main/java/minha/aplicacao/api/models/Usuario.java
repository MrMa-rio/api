package minha.aplicacao.api.models;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.UsuarioDTO;

@Table(name = "tb_usuario")
@Entity
@Getter
@NoArgsConstructor

public class Usuario extends Pessoa{


    @Id
    private int idUsuario;
    private String senha;
    private int nivel_acesso;


    public Usuario(UsuarioDTO usuarioDTO) {

        super(usuarioDTO.nome(), usuarioDTO.data_nascimento(),usuarioDTO.imagem_64(), usuarioDTO.cpf(), usuarioDTO.email());
        this.senha = usuarioDTO.senha();
        this.nivel_acesso = usuarioDTO.nivel_acesso();
        this.idUsuario = usuarioDTO.idUsuario();
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

}
