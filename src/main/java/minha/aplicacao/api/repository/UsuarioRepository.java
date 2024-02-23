package minha.aplicacao.api.repository;


import minha.aplicacao.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {



}
