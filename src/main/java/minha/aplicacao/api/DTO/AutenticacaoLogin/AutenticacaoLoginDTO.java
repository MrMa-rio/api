package minha.aplicacao.api.DTO.AutenticacaoLogin;

import jakarta.validation.constraints.NotEmpty;

public record AutenticacaoLoginDTO(
        @NotEmpty
        String email,
        @NotEmpty
        String senha
) {
}
