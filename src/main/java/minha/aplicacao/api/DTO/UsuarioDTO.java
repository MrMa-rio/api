package minha.aplicacao.api.DTO;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record UsuarioDTO (
        @NotBlank
        String nome,
        @NotNull
        LocalDate data_nascimento,
        @Id
        int idUsuario,
        int status,
        @NotBlank
        String senha,
        String imagem_64,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        int nivel_acesso,
        @Email
        String email) {

}
