package minha.aplicacao.api.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;

public record UsuarioDTO (
        @NotBlank @JsonProperty
        String nome,
        @NotNull @JsonProperty
        String dataNascimento,
        @Id @JsonProperty
        int idUsuario,
        @NotBlank @JsonProperty
        String senha,
        String imagem_64,
        @NotBlank @JsonProperty @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull @JsonProperty
        int nivel_acesso,
        @Email @JsonProperty
        String email) {

}
