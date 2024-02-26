package minha.aplicacao.api.DTO;

import java.time.LocalDateTime;

public record PessoaDTO(String nome, LocalDateTime data_nascimento, String imagem_64, String cpf, int nivel_acesso, String email) {
}
