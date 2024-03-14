package minha.aplicacao.api.DTO;

import java.time.LocalDateTime;

public record PessoaDTO(String nome, LocalDateTime dataNascimento, String imagem64, String cpf, int nivelAcesso, String email) {
}
