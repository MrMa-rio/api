package minha.aplicacao.api.DTO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public record ItemCreateDTO(
        @Id
        Integer idItem,
        @NotNull
        Double precoUnitario,
        @NotNull
        String nome,
        String imagem64
) {
}
