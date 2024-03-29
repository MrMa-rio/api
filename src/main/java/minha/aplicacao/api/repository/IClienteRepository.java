package minha.aplicacao.api.repository;


import minha.aplicacao.api.models.Cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    public UserDetails findByEmail(String email);
}
