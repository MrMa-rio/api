package minha.aplicacao.api.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@NoArgsConstructor
public class Pessoa {

    private String nome;
    private LocalDateTime data_nascimento;

    private String imagem_64;
    private String cpf;
    private String email;

//    public Pessoa(String nome, LocalDateTime localDateTime, String cpf, String email, String s) {
//    }

    public Pessoa(String nome, LocalDateTime data_nascimento, String imagem_64, String cpf, String email) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.imagem_64 = imagem_64;
        this.cpf = cpf;
        this.email = email;
    }
}
