package com.GrupoProga3.Gestor_Salud.auth.jwt;

import com.GrupoProga3.Gestor_Salud.auth.credenciales.EntidadCredencial;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ServicioJWT implements  IServicioJWT {

    @Value("${jwt.secret}")
    private String jwtSecretKey;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;
    @Value("${jwt.refresh-expiration}")
    private Long refreshTokenExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(EntidadCredencial user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().getRole().name());
        claims.put("permisos", user.getRole().getPermisos()
                .stream()
                .map(p->p.getPermiso().name())
                .toList());
        /*List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roles);

         */
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public List<GrantedAuthority> extractAuthorities(String token) {
        Claims claims = extractAllClaims(token);

        List<GrantedAuthority> authorities = new ArrayList<>();

        String role = claims.get("role", String.class);
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        List<?> rawPermisos = claims.get("permisos", List.class);
        if (rawPermisos != null) {
            rawPermisos.stream()
                    .map(Object::toString)
                    .map(SimpleGrantedAuthority::new)
                    .forEach(authorities::add);
        }

        return authorities;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()))
                && !isTokenExpired(token)
                && userDetails.isAccountNonExpired()
                && userDetails.isEnabled();
    }

    @Override
    public String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts
                .builder().claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()
                        + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public SecretKey getSignInKey() {
        byte[] keyBytes = this.jwtSecretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "refresh");
        return buildToken(claims, userDetails, refreshTokenExpiration);
    }

    public boolean validateRefreshToken(String refreshToken, UserDetails
            userDetails) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(refreshToken)
                    .getPayload();
            String type = claims.get("type", String.class);

            if(!"refresh".equals(type)) {
                return false;
            }

            final String username = claims.getSubject();
            return (username.equals(userDetails.getUsername())) &&
                    !isTokenExpired(refreshToken);
        } catch (JwtException e) {
            return false; // Invalid token

        }
    }
}