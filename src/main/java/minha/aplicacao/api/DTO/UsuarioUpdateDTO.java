package minha.aplicacao.api.DTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record UsuarioUpdateDTO (
        String nome,
        String dataNascimento,
        @Id @NotNull
        int idUsuario,
        Integer status,
        String senha,
        String imagem64,
        Integer nivelAcesso
){}
