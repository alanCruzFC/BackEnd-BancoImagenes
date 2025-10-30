package com.fc.backendbancoimagenes.service;


import java.util.List;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fc.backendbancoimagenes.model.Usuario;
import com.fc.backendbancoimagenes.repository.UserRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UsuarioDetailsService(UserRepository repo) {
        this.userRepository = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        String role = "ROLE_" + user.getRol().toUpperCase();

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority(role))
        );
    }

}
