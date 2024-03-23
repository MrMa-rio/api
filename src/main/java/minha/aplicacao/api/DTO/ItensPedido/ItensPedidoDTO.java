package minha.aplicacao.api.DTO.ItensPedido;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public record ItensPedidoDTO(
        @Id
        Integer id,
        @NotNull
        Integer fkPedido,
        @NotNull
        Integer fkItem,
        @NotNull
        Integer quantidade,

        Double precoUnitario
) {}
