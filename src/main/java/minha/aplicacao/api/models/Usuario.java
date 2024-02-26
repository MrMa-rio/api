package minha.aplicacao.api.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.UsuarioDTO;

import java.time.LocalDateTime;

@Table(name = "tb_usuario")
@Entity
@Getter
@NoArgsConstructor
//@AllArgsConstructor

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

}
