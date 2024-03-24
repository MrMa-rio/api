package minha.aplicacao.api.DTO.Usuario;

import minha.aplicacao.api.models.Usuario.Usuario;

//public record UsuarioCommonDTO(
//        String nome,
//        String dataNascimento,
//        Integer idUsuario,
//        StatusEnum status,
//        String imagem64,
//        String cpf,
//        int nivelAcesso,
//        String email) {
//
//
//}
public record UsuarioCommonDTO(Usuario usuario) {
}
