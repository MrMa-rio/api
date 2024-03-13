package minha.aplicacao.api.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.ClienteCreateDTO;
import minha.aplicacao.api.DTO.ClienteUpdateDTO;
import org.jetbrains.annotations.NotNull;

@Table(name = "tb_cliente")
@Entity
@Getter
@NoArgsConstructor

public class Cliente extends Pessoa{

    private int status;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    private String senha;
    private int nivel_acesso;

    public Cliente(@NotNull ClienteCreateDTO clienteCreateDTO) {
        super(clienteCreateDTO.nome(), clienteCreateDTO.data_nascimento(), clienteCreateDTO.imagem_64(), clienteCreateDTO.cpf(), clienteCreateDTO.email());
        this.senha = clienteCreateDTO.senha();
        this.nivel_acesso = clienteCreateDTO.nivel_acesso();
        this.idCliente = clienteCreateDTO.idCliente();
        this.status = clienteCreateDTO.status();
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
    public void updateCliente(ClienteUpdateDTO clienteUpdateDTO){
        if(clienteUpdateDTO.dataNascimento() != null){
            setDataNascimento(clienteUpdateDTO.dataNascimento());
        }
        if(clienteUpdateDTO.nome() != null){
            setNome(clienteUpdateDTO.nome());
        }
        if(clienteUpdateDTO.nivel_acesso() != null){
            this.nivel_acesso = clienteUpdateDTO.nivel_acesso();
        }
        if(clienteUpdateDTO.senha() != null){
            this.senha = clienteUpdateDTO.senha();
        }
        if(clienteUpdateDTO.imagem_64() != null){
            this.setImagem_64(clienteUpdateDTO.imagem_64());
        }
        if(clienteUpdateDTO.status() != null){
            this.status = clienteUpdateDTO.status();
        }
    }

    public void deleteCliente() {
        this.status = 1;
    }
}
