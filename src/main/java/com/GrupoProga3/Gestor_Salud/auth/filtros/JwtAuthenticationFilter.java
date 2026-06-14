package com.GrupoProga3.Gestor_Salud.auth.filtros;

import com.GrupoProga3.Gestor_Salud.auth.jwt.ServicioJWT;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ServicioJWT servicioJWT;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        try {
            final String username = servicioJWT.extractUsername(jwt);

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();


            if (username != null && authentication == null) {
                List<GrantedAuthority> authorities =
                        servicioJWT.extractAuthorities(jwt);

                UsernamePasswordAuthenticationToken auhtToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);

                auhtToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auhtToken);
            }
        } catch (JwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(String.format("{\"error\":\"Token JWT invalido o expirado\", \"status\": %d, \"path\": \"%s\"}",
                    HttpServletResponse.SC_UNAUTHORIZED,
                    request.getRequestURI()));
            response.getWriter().flush();
            return;
        }
        filterChain.doFilter(request, response);
    }
}
