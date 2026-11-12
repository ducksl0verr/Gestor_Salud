package com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion;

import com.GrupoProga3.Gestor_Salud.auth.credenciales.EntidadCredencial;
import com.GrupoProga3.Gestor_Salud.auth.credenciales.RepositorioCredencial;
import com.GrupoProga3.Gestor_Salud.common.excepciones.TokenRecuperacionInvalidoException;
import com.GrupoProga3.Gestor_Salud.features.Notificaciones.ServicioEmail;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Repositorio.RepositorioUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class ServicioRecuperacion {
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioTokenRecuperacion repositorioToken;
    private final PasswordEncoder passwordEncoder;
    private final ServicioEmail servicioEmail;
    private final RepositorioCredencial repositorioCredencial;

    @Value("${app.frontend.url}")
    private String urlFront;

    @Value("${app.recuperacion.minutos-validez}")
    private long minutosValidez;

    @Value("${app.recuperacion.max-solicitudes-por-hora}")
    private long maxSolicitudesPorHora;

    private static final SecureRandom RANDOM = new SecureRandom();

    @Transactional
    public void solicitar(String email) {
        // Si el email no existe, no hacemos nada y no avisamos.
        repositorioUsuario.findByContactoEmail(email).ifPresent(usuario -> {

            EntidadCredencial credencial = usuario.getCredencial();
            if (credencial == null || !credencial.isEnabled()) return;

            // Límite de solicitudes por hora, también en silencio
            long recientes = repositorioToken.countByUsuarioAndCreadoEnAfter(
                    usuario, LocalDateTime.now().minusHours(1));
            if (recientes >= maxSolicitudesPorHora) return;

            // Un solo token válido a la vez
            repositorioToken.invalidarPendientes(usuario);

            String token = generarToken();

            EntidadTokenRecuperacion entidad = new EntidadTokenRecuperacion();
            entidad.setUsuario(usuario);
            entidad.setTokenHash(hash(token));
            entidad.setCreadoEn(LocalDateTime.now());
            entidad.setVencimiento(LocalDateTime.now().plusMinutes(minutosValidez));
            repositorioToken.save(entidad);

            String link = urlFront + "/restablecer?token=" + token;
            servicioEmail.enviarMail(
                    email,
                    "Recuperación de contraseña",
                    "Recibimos una solicitud para restablecer tu contraseña.\n\n" +
                            "Ingresá a este enlace para elegir una nueva (vence en " + minutosValidez + " minutos):\n" +
                            link + "\n\n" +
                            "Si no fuiste vos, ignorá este mensaje.");
        });
    }

    @Transactional
    public void restablecer(String token, String nuevaContrasena) {

        EntidadTokenRecuperacion entidad = repositorioToken
                .findByTokenHash(hash(token))
                .orElseThrow(() -> new TokenRecuperacionInvalidoException(""));

        if (entidad.isUsado() || entidad.getVencimiento().isBefore(LocalDateTime.now())) {
            throw new TokenRecuperacionInvalidoException("El enlace de recuperación no es válido o ya venció.");
        }

        EntidadCredencial credencial = entidad.getUsuario().getCredencial();
        if(credencial == null || !credencial.isEnabled()){
            throw new TokenRecuperacionInvalidoException("El enlace de recuperación no es válido o ya venció.");
        }

        credencial.setPassword(passwordEncoder.encode(nuevaContrasena));
        credencial.setRefreshToken(null); //cierra las sesiones renovables
        repositorioCredencial.save(credencial);

        entidad.setUsado(true);
        repositorioToken.save(entidad);
    }


    private String generarToken() {
        byte[] bytes = new byte[32];
        RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private String hash(String token) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(md.digest(token.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
