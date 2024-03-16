package minha.aplicacao.api.DTO.Pedido;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record PedidoUpdateDTO(
        @Id @NotNull @NumberFormat
        Integer idPedido,
        @NotNull @NumberFormat
        Integer statusPedido
) {
}
