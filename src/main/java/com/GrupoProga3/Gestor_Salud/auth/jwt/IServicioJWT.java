package com.GrupoProga3.Gestor_Salud.auth.jwt;

import com.GrupoProga3.Gestor_Salud.auth.credenciales.EntidadCredencial;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface IServicioJWT {
    String extractUsername(String token);

    String generateToken(EntidadCredencial credencial);

    List<GrantedAuthority> extractAuthorities(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    );

    SecretKey getSignInKey();

    boolean isTokenExpired(String token);
}
