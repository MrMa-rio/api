package minha.aplicacao.api.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.UsuarioDTO;

import java.time.LocalDateTime;

@Table(name = "tb_usuario")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class Usuario {

    private String nome;
    private LocalDateTime data_nascimento;
    @Id
    private int idUsuario;
    private String senha;
    private String imagem_64;
    private String cpf;
    private int nivel_acesso;
    private String email;

    public Usuario(UsuarioDTO usuarioDTO) {
        this.nome = usuarioDTO.nome();
        this.cpf = usuarioDTO.cpf();
        this.idUsuario = usuarioDTO.idUsuario();
        this.imagem_64 = usuarioDTO.imagem_64();
        this.email = usuarioDTO.email();
        this.data_nascimento = usuarioDTO.data_nascimento();
        this.nivel_acesso = usuarioDTO.nivel_acesso();
        this.senha = usuarioDTO.senha();
    }
}
