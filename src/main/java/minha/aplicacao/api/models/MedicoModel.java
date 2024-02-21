package minha.aplicacao.api.models;

import minha.aplicacao.api.enumerations.EspecialidadeEnum;

public record MedicoModel(String nome, String email, String crm, EspecialidadeEnum especialidade, EnderecoModel endereco ) {

}
