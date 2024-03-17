package minha.aplicacao.api.models.ItensPedido;

import jakarta.persistence.*;
import minha.aplicacao.api.DTO.ItensPedido.ItensPedidoDTO;

@Table(name = "tb_itenspedido")
@Entity
public class ItensPedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fk_pedido")
    private Integer fkPedido;
    @Column(name = "fk_item")
    private Integer fkItem;
    private Integer quantidade;
    @Column(name = "preco_unitario")
    private Double precoUnitario;

    public ItensPedido(ItensPedidoDTO itensPedidoDTO) {
        this.id = itensPedidoDTO.id();
        this.fkPedido = itensPedidoDTO.fkPedido();
        this.fkItem = itensPedidoDTO.fkItem();
        this.quantidade = itensPedidoDTO.quantidade();
        this.precoUnitario = itensPedidoDTO.precoUnitario();
    }
}
