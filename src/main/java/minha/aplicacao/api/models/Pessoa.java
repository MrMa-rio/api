package minha.aplicacao.api.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Pessoa {

    private String nome;
    private String dataNascimento;

    private String imagem_64;
    private String cpf;
    private String email;


    public Pessoa(String nome, String dataNascimento, String imagem_64, String cpf, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.imagem_64 = imagem_64;
        this.cpf = cpf;
        this.email = email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setImagem_64(String imagem_64) {
        this.imagem_64 = imagem_64;
    }
}
