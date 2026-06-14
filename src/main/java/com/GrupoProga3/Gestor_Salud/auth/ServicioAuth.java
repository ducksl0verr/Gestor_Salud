package com.GrupoProga3.Gestor_Salud.auth;

import com.GrupoProga3.Gestor_Salud.auth.DTOs.AuthPedido;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.AuthRespuesta;
import com.GrupoProga3.Gestor_Salud.auth.credenciales.EntidadCredencial;
import com.GrupoProga3.Gestor_Salud.auth.credenciales.RepositorioCredencial;
import com.GrupoProga3.Gestor_Salud.auth.jwt.ServicioJWT;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicioAuth {

    private final AuthenticationManager authenticationManager;
    private final RepositorioCredencial repositorioCredencial;
    private final ServicioJWT servicioJWT;

    public AuthRespuesta authenticate(AuthPedido input) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.username(), input.password()));
        EntidadCredencial user = repositorioCredencial.findByUsername(input.username())
                .orElseThrow(() -> new EntidadNoEncontradaException("Usuario",
                        "No encontrado",
                        1l,
                        "No se ha encontrado el usuario"));
        String accessToken = servicioJWT.generateToken(user);
        String refreshToken = servicioJWT.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        repositorioCredencial.save(user);
        return new AuthRespuesta(accessToken, refreshToken);
    }

    public AuthRespuesta refreshAccessToken (String refreshToken) {
        String username = servicioJWT.extractUsername(refreshToken);
        EntidadCredencial user = repositorioCredencial.findByUsername(username)
                .orElseThrow(()-> new EntidadNoEncontradaException("Usuario",
                        "No encontrado",
                        1l,
                        "No se ha encontrado el usuario"));
        if (!user.getRefreshToken().equals(refreshToken)) {
            throw new IllegalArgumentException("Refresh token no valido");
        }
        if (!servicioJWT.validateRefreshToken(refreshToken, user)){
            throw new IllegalArgumentException("Refresh token no valido");
        }
        String newAccessToken = servicioJWT.generateToken(user);
        String newRefreshToken = servicioJWT.generateRefreshToken(user);
        user.setRefreshToken(newRefreshToken);
        repositorioCredencial.save(user);
        return new AuthRespuesta(newAccessToken, newRefreshToken);
    }

}
