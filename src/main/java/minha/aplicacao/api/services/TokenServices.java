package minha.aplicacao.api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import minha.aplicacao.api.models.Cliente.Cliente;
import minha.aplicacao.api.models.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServices {

    @Value("${api.services.TokenServices.secretKey}")
    private String secretKey;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC512(secretKey);
            return JWT.create()
                    .withIssuer("API Restaurante")
                    .withSubject(usuario.getUsername())
                    .withClaim("email",usuario.getUsername())
                    .withClaim("type","USUARIO")
                    .withClaim("id",usuario.getIdUsuario())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("Erro ao gerar token",exception);
        }
    }

    public String gerarToken(Cliente cliente){
        try {
            Algorithm algoritmo = Algorithm.HMAC512(secretKey);
            return JWT.create()
                    .withIssuer("API Restaurante")
                    .withSubject(cliente.getUsername())
                    .withClaim("email",cliente.getUsername())
                    .withClaim("type","CLIENTE")
                    .withClaim("idCliente",cliente.getIdCliente())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("Erro ao gerar token",exception);
        }
    }

    public String getSubject(String token){

        try {
            Algorithm algoritmo = Algorithm.HMAC512(secretKey);
            return JWT.require(algoritmo)
                    .withIssuer("API Restaurante")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw  new RuntimeException(exception.getMessage());
        }
    }

    public Claim getClaim(String token){

        try {
            Algorithm algoritmo = Algorithm.HMAC512(secretKey);
            return JWT.require(algoritmo)
                    .withIssuer("API Restaurante")
                    .build()
                    .verify(token)
                    .getClaim("type");
        } catch (JWTVerificationException exception){
            throw  new RuntimeException(exception.getMessage());
        }
    }

    public Instant dataExpiracao(){
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}
