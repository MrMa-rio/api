package minha.aplicacao.api.DTO.Item;

import jakarta.persistence.Id;

public record ItemUpdateDTO(
        @Id
        Integer idItem,
        Double precoUnitario,
        String nome,
        String imagem64
) {
}
