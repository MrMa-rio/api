package minha.aplicacao.api.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;

public record ClienteCreateDTO(
        @NotBlank
        String nome,
        @NotNull
        String data_nascimento,
        @Id
        int idCliente,
        int status,
        @NotBlank
        String senha,
        String imagem_64,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        int nivel_acesso,
        @Email @NotNull
        String email) {

}
