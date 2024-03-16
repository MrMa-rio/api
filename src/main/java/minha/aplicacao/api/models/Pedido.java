package minha.aplicacao.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.Pedido.PedidoCreateDTO;
import java.sql.Date;

@Table(name = "tb_pedido")
@Entity
@Getter
@NoArgsConstructor
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Column(name = "data_pedido")
    private Date dataPedido;
    @Column(name = "fk_cliente")
    private Integer fkCliente;
    private String descricao;
    @Column(name = "status_pedido")
    private Integer statusPedido;

    public Pedido(PedidoCreateDTO pedidoCreateDTO){
        this.idPedido = pedidoCreateDTO.idPedido();
        this.dataPedido = pedidoCreateDTO.dataPedido();
        this.fkCliente = pedidoCreateDTO.fkCliente();
        this.descricao = pedidoCreateDTO.descricao();
        this.statusPedido = pedidoCreateDTO.statusPedido();
    }
}
