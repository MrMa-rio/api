package minha.aplicacao.api.models.Cliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.Cliente.ClienteCreateDTO;
import minha.aplicacao.api.DTO.Cliente.ClienteUpdateDTO;
import minha.aplicacao.api.models.Pessoa.Pessoa;
import org.jetbrains.annotations.NotNull;

@Table(name = "tb_cliente")
@Entity
@Getter
@NoArgsConstructor

public class Cliente extends Pessoa {

    private StatusEnum status;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    private String senha;
    @Column(name = "nivel_acesso")
    private int nivelAcesso;

    public Cliente(@NotNull ClienteCreateDTO clienteCreateDTO) {
        super(clienteCreateDTO.nome(), clienteCreateDTO.dataNascimento(), clienteCreateDTO.imagem64(), clienteCreateDTO.cpf(), clienteCreateDTO.email());
        this.senha = clienteCreateDTO.senha();
        this.nivelAcesso = clienteCreateDTO.nivelAcesso();
        this.idCliente = clienteCreateDTO.idCliente();
        this.status = StatusEnum.ATIVO;
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
        if(clienteUpdateDTO.nivelAcesso() != null){
            this.nivelAcesso = clienteUpdateDTO.nivelAcesso();
        }
        if(clienteUpdateDTO.senha() != null){
            this.senha = clienteUpdateDTO.senha();
        }
        if(clienteUpdateDTO.imagem64() != null){
            this.setImagem64(clienteUpdateDTO.imagem64());
        }
        if(clienteUpdateDTO.status() != null){

            this.status = clienteUpdateDTO.status();
        }
    }

    public void deleteCliente() {
        this.status = StatusEnum.INATIVO;
    }
}
