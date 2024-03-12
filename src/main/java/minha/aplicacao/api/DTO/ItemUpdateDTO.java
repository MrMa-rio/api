package minha.aplicacao.api.DTO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public record ItemUpdateDTO(
        @Id
        Integer idItem,

        Double precoUnitario,

        String nome,
        String imagem64
) {
}
