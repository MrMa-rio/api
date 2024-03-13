package minha.aplicacao.api.DTO;

import jakarta.persistence.Id;

public record ItemUpdateDTO(
        @Id
        Integer idItem,
        Double precoUnitario,
        String nome,
        String imagem64
) {
}
