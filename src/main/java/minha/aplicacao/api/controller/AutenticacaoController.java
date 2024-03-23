package minha.aplicacao.api.controller;

import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.AutenticacaoLogin.AutenticacaoLoginDTO;
import minha.aplicacao.api.DTO.Token.TokenDTO;
import minha.aplicacao.api.models.Cliente.Cliente;
import minha.aplicacao.api.models.Usuario.Usuario;
import minha.aplicacao.api.services.TokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth", "/auth/"})
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenServices tokenServices;

    @PostMapping("usuario/register")
    public ResponseEntity<?> setLoginUsuario(@RequestBody @Valid AutenticacaoLoginDTO autenticacaoLoginDTO){
        var token = new UsernamePasswordAuthenticationToken(
                autenticacaoLoginDTO.email(),
                autenticacaoLoginDTO.senha()
        );
        var auth = authenticationManager.authenticate(token);
        TokenDTO tokenDTO = new TokenDTO(200, tokenServices.gerarToken((Usuario) auth.getPrincipal()));
        return ResponseEntity.ok(tokenDTO);
    }
    @PostMapping("cliente/register")
    public ResponseEntity<?> setLoginCliente(@RequestBody @Valid AutenticacaoLoginDTO autenticacaoLoginDTO){
        var token = new UsernamePasswordAuthenticationToken(
                autenticacaoLoginDTO.email(),
                autenticacaoLoginDTO.senha()
        );
        var auth = authenticationManager.authenticate(token);
        TokenDTO tokenDTO = new TokenDTO(200, tokenServices.gerarToken((Cliente) auth.getPrincipal()));
        return ResponseEntity.ok(tokenDTO);
    }
}
