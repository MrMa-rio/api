package minha.aplicacao.api.models;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.UsuarioCreateDTO;
import minha.aplicacao.api.DTO.UsuarioUpdateDTO;
import org.jetbrains.annotations.NotNull;

@Table(name = "tb_usuario")
@Entity
@Getter
@NoArgsConstructor

public class Usuario extends Pessoa{

    private int status;
    @Id
    private int idUsuario;
    private String senha;
    private int nivel_acesso;

    public Usuario(@NotNull UsuarioCreateDTO usuarioCreateDTO) {
        super(usuarioCreateDTO.nome(), usuarioCreateDTO.data_nascimento(), usuarioCreateDTO.imagem_64(), usuarioCreateDTO.cpf(), usuarioCreateDTO.email());
        this.senha = usuarioCreateDTO.senha();
        this.nivel_acesso = usuarioCreateDTO.nivel_acesso();
        this.idUsuario = usuarioCreateDTO.idUsuario();
        this.status = usuarioCreateDTO.status();
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
    public void updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO){
        if(usuarioUpdateDTO.dataNascimento() != null){
            setDataNascimento(usuarioUpdateDTO.dataNascimento());
        }
        if(usuarioUpdateDTO.nome() != null){
            setNome(usuarioUpdateDTO.nome());
        }
        if(usuarioUpdateDTO.nivel_acesso() != null){
            this.nivel_acesso = usuarioUpdateDTO.nivel_acesso();
        }
        if(usuarioUpdateDTO.senha() != null){
            this.senha = usuarioUpdateDTO.senha();
        }
        if(usuarioUpdateDTO.imagem_64() != null){
            this.setImagem_64(usuarioUpdateDTO.imagem_64());
        }
        if(usuarioUpdateDTO.status() != null){
            this.status = usuarioUpdateDTO.status();
        }
    }

    public void deleteUsuario() {
        this.status = 1;
    }
}
