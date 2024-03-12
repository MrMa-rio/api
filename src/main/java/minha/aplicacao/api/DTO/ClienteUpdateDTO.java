package minha.aplicacao.api.DTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record ClienteUpdateDTO(
        String nome,
        String dataNascimento,
        @Id @NotNull
        int idCliente,
        Integer status,
        String senha,
        String imagem_64,
        Integer nivel_acesso
){}
