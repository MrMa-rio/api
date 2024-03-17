package minha.aplicacao.api.repository;

import minha.aplicacao.api.models.ItensPedido.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItensPedidoRepository extends JpaRepository<ItensPedido, Integer> {
}
