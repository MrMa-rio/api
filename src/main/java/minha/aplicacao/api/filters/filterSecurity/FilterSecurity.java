package minha.aplicacao.api.filters.filterSecurity;

import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import minha.aplicacao.api.repository.IClienteRepository;
import minha.aplicacao.api.repository.IUsuarioRepository;
import minha.aplicacao.api.services.TokenServices;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterSecurity extends OncePerRequestFilter {
    @Autowired
    private TokenServices tokenServices;
    @Autowired
    IUsuarioRepository iUsuarioRepository;
    @Autowired
    IClienteRepository iClienteRepository;



    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authorizationHeader = getToken(request);
        if(!authorizationHeader.isEmpty()) {
            String subject = tokenServices.getSubject(authorizationHeader);
            Claim claim = tokenServices.getClaim(authorizationHeader);
            UserDetails userDetails;
            if(claim.asString().equals("USUARIO")){
                userDetails = iUsuarioRepository.findByEmail(subject);
            }
            else{
                userDetails = iClienteRepository.findByEmail(subject);
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    public String getToken(HttpServletRequest request){

        String authorizationToken = request.getHeader("Authorization");
        if(authorizationToken != null){
            return authorizationToken.replace("Bearer", "");
        }
        return "";
    }
}
