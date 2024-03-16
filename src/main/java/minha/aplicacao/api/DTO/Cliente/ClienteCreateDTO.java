package minha.aplicacao.api.DTO.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;

public record ClienteCreateDTO(
        @NotBlank
        String nome,
        @NotNull
        String dataNascimento,
        @Id
        int idCliente,
        int status,
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
