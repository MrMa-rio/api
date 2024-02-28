package minha.aplicacao.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@NoArgsConstructor
public class Pessoa {

    private String nome;
    @Column(name = "data_nascimento")
    private String dataNascimento;

    private String imagem_64;
    private String cpf;
    private String email;


    public Pessoa(String nome, String dataNascimento, String imagem_64, String cpf, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento.toString();
        this.imagem_64 = imagem_64;
        this.cpf = cpf;
        this.email = email;
    }
}
