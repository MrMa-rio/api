package minha.aplicacao.api.DTO;
import java.time.LocalDateTime;

public record UsuarioDTO (String nome, LocalDateTime data_nascimento, int idUsuario, String senha, String imagem_64, String cpf, int nivel_acesso, String email) {

}
