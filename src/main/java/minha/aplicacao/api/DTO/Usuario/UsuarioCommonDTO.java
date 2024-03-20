package minha.aplicacao.api.DTO.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import minha.aplicacao.api.models.Usuario.StatusEnum;
import org.springframework.data.annotation.Id;

public record UsuarioCommonDTO(
        String nome,
        String dataNascimento,
        Integer idUsuario,
        StatusEnum status,
        String imagem64,
        String cpf,
        int nivelAcesso,
        String email) {

}
