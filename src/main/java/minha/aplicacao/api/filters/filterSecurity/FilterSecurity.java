package minha.aplicacao.api.filters.filterSecurity;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authorizationHeader = getToken(request);
        if(authorizationHeader != null) {
            String subject = tokenServices.getSubject(authorizationHeader);
            UserDetails usuario = iUsuarioRepository.findByEmail(subject);
            Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    public String getToken(HttpServletRequest request){

        String authorizationToken = request.getHeader("Authorization");
        if(authorizationToken != null){
            return authorizationToken.replace("Bearer", "");
        }
        return null;
    }
}
