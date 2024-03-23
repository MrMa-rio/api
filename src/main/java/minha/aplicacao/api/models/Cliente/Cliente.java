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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Collection;

@Table(name = "tb_cliente")
@Entity
@Getter
@NoArgsConstructor

public class Cliente extends Pessoa implements UserDetails {

    private StatusEnum status;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    private String senha;
    @Column(name = "nivel_acesso")
    private int nivelAcesso;
    private String email;

    public Cliente(@NotNull ClienteCreateDTO clienteCreateDTO) {
        super(clienteCreateDTO.nome(), clienteCreateDTO.dataNascimento(), clienteCreateDTO.imagem64(), clienteCreateDTO.cpf());
        this.senha = new BCryptPasswordEncoder().encode(clienteCreateDTO.senha());
        this.nivelAcesso = clienteCreateDTO.nivelAcesso();
        this.idCliente = clienteCreateDTO.idCliente();
        this.status = StatusEnum.ATIVO;
        this.email = clienteCreateDTO.email();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }
}
