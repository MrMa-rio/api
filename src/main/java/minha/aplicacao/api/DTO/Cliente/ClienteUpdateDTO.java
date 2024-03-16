package minha.aplicacao.api.DTO.Cliente;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.NumberFormat;

public record ClienteUpdateDTO(
        String nome,
        String dataNascimento,
        @Id @NotNull @NumberFormat
        int idCliente,
        Integer status,
        String senha,
        String imagem64,
        Integer nivelAcesso
){}
