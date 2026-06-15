package com.GrupoProga3.Gestor_Salud.auth.credenciales;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final RepositorioCredencial repositorioCredencial;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        EntidadCredencial credencial = repositorioCredencial
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(credencial.getRole().getRole().name()));

        credencial.getRole().getPermisos().forEach(
                permiso -> authorities.add(new SimpleGrantedAuthority(permiso.getPermiso().name()))
        );

        return new User(credencial.getUsername(), credencial.getPassword(), authorities);
    }
}
