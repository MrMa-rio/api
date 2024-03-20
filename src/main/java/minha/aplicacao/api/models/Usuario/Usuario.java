package minha.aplicacao.api.models.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.Usuario.UsuarioCreateDTO;
import minha.aplicacao.api.DTO.Usuario.UsuarioUpdateDTO;
import minha.aplicacao.api.models.Pessoa.Pessoa;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

@Table(name = "tb_usuario")
@Entity
@Getter
@NoArgsConstructor

public class Usuario extends Pessoa implements UserDetails {

    private StatusEnum status;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @JsonIgnore
    private String senha;
    @Column(name = "nivel_acesso")
    private int nivelAcesso;
    private String email;

    public Usuario(@NotNull UsuarioCreateDTO usuarioCreateDTO) {
        super(usuarioCreateDTO.nome(), usuarioCreateDTO.dataNascimento(), usuarioCreateDTO.imagem64(), usuarioCreateDTO.cpf());
        this.senha = new BCryptPasswordEncoder().encode(usuarioCreateDTO.senha());
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

    public Integer getIdUsuario(){
        return this.idUsuario;
    }

    @Override @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override @JsonIgnore
    public String getPassword() {
        return this.senha;
    }

    @Override @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    @Override @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
