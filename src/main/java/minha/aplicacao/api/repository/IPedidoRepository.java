package minha.aplicacao.api.repository;

import minha.aplicacao.api.models.Pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface IPedidoRepository extends JpaRepository<Pedido, Integer> {

    public ArrayList<Pedido> findByFkCliente(Integer fkCliente);
}
