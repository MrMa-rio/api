package minha.aplicacao.api.DTO.Pedido;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.sql.Date;

public record PedidoCreateDTO(
        @Id
        Integer idPedido,
        @NotNull @DateTimeFormat
        Date dataPedido,
        @NotNull @NumberFormat
        Integer fkCliente,
        String descricao,
        @NotNull @NumberFormat
        Integer statusPedido ) {
}
