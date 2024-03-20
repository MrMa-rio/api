package minha.aplicacao.api.DTO.Usuario;
import jakarta.validation.constraints.*;
import minha.aplicacao.api.models.Usuario.StatusEnum;
import org.springframework.data.annotation.Id;

public record UsuarioCreateDTO(
        @NotBlank
        String nome,
        @NotNull
        String dataNascimento,
        @Id
        int idUsuario,
        StatusEnum status,
        @NotBlank
        String senha,
        String imagem64,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        int nivelAcesso,
        @Email @NotNull
        String email) {

}
