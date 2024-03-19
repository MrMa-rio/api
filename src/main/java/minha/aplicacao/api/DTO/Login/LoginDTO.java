package minha.aplicacao.api.DTO.Login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(

        @NotEmpty
        String email,
        @NotEmpty
        String senha
) {

}
