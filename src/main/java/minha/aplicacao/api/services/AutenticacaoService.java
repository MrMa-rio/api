package minha.aplicacao.api.services;

import minha.aplicacao.api.repository.IClienteRepository;
import minha.aplicacao.api.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IClienteRepository iClienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(iUsuarioRepository.findByEmail(email) != null){
            return iUsuarioRepository.findByEmail(email);
        }
        return iClienteRepository.findByEmail(email);
    }
}
