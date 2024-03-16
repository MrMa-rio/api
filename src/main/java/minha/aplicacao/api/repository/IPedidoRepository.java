package minha.aplicacao.api.repository;

import minha.aplicacao.api.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido, Integer> {
}
