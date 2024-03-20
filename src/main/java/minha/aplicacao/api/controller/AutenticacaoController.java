package minha.aplicacao.api.controller;

import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.AutenticacaoLogin.AutenticacaoLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> setLogin(@RequestBody @Valid AutenticacaoLoginDTO autenticacaoLoginDTO){
        var token = new UsernamePasswordAuthenticationToken(
                autenticacaoLoginDTO.email(),
                autenticacaoLoginDTO.senha()
        );
        var auth = authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
