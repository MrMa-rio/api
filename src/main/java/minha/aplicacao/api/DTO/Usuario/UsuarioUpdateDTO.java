package minha.aplicacao.api.DTO.Usuario;

import jakarta.validation.constraints.NotNull;
import minha.aplicacao.api.models.Usuario.StatusEnum;
import org.springframework.data.annotation.Id;

public record UsuarioUpdateDTO (
        String nome,
        String dataNascimento,
        @Id @NotNull
        int idUsuario,
        StatusEnum status,
        String senha,
        String imagem64,
        Integer nivelAcesso
){}
