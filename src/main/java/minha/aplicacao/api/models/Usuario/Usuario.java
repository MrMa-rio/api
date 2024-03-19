package minha.aplicacao.api.models.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minha.aplicacao.api.DTO.Usuario.UsuarioCreateDTO;
import minha.aplicacao.api.DTO.Usuario.UsuarioUpdateDTO;
import minha.aplicacao.api.models.Pessoa.Pessoa;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Table(name = "tb_usuario")
@Entity
@Getter
@Setter
@NoArgsConstructor

public class Usuario extends Pessoa implements UserDetails {

    private StatusEnum status;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @Setter
    private String senha;
    @Column(name = "nivel_acesso")
    private int nivelAcesso;
    private String email;


    public Usuario(@NotNull UsuarioCreateDTO usuarioCreateDTO) {
        super(usuarioCreateDTO.nome(), usuarioCreateDTO.dataNascimento(), usuarioCreateDTO.imagem64(), usuarioCreateDTO.cpf(), usuarioCreateDTO.email());
        this.senha = usuarioCreateDTO.senha();
        this.nivelAcesso = usuarioCreateDTO.nivelAcesso();
        this.idUsuario = usuarioCreateDTO.idUsuario();
        this.status = StatusEnum.ATIVO;
        this.email = usuarioCreateDTO.email();
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
        if(usuarioUpdateDTO.nivelAcesso() != null){
            this.nivelAcesso = usuarioUpdateDTO.nivelAcesso();
        }
        if(usuarioUpdateDTO.senha() != null){
            this.senha = usuarioUpdateDTO.senha();
        }
        if(usuarioUpdateDTO.imagem64() != null){
            this.setImagem64(usuarioUpdateDTO.imagem64());
        }
        if(usuarioUpdateDTO.status() != null){
            this.status = usuarioUpdateDTO.status();
        }
    }

    public void deleteUsuario() {
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

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
