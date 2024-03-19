package minha.aplicacao.api.services;

import minha.aplicacao.api.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoServices implements UserDetailsService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return iUsuarioRepository.findByEmail(email);
    }
}
