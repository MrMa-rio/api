package minha.aplicacao.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter


public class Pessoa {

    private String nome;
    @Column(name = "data_nascimento")
    private String dataNascimento;

    @Column(name = "imagem_64")
    private String imagem64;
    private String cpf;
    private String email;

    public Pessoa(){
        this.nome = "";
        this.dataNascimento = "";
        this.imagem64= "";
        this.cpf = "";
        this.email = "";
    }

    public Pessoa(String nome, String dataNascimento, String imagem_64, String cpf, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.imagem64 = imagem_64;
        this.cpf = cpf;
        this.email = email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setImagem64(String imagem_64) {
        this.imagem64 = imagem64;
    }

    @JsonIgnore
    public boolean isValid(){
        return !cpf.isEmpty();
    }
}
