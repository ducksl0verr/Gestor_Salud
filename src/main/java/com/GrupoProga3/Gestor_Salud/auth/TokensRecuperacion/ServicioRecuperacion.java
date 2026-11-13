package com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion;

import com.GrupoProga3.Gestor_Salud.auth.credenciales.EntidadCredencial;
import com.GrupoProga3.Gestor_Salud.auth.credenciales.RepositorioCredencial;
import com.GrupoProga3.Gestor_Salud.common.excepciones.TokenRecuperacionInvalidoException;
import com.GrupoProga3.Gestor_Salud.features.Notificaciones.ServicioEmail;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Repositorio.RepositorioUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicioRecuperacion {

    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioTokenRecuperacion repositorioToken;
    private final PasswordEncoder passwordEncoder;
    private final ServicioEmail servicioEmail;
    private final RepositorioCredencial repositorioCredencial;
    private final ApplicationEventPublisher eventPublisher;

    @Value("${app.frontend.url}")
    private String urlFront;

    @Value("${app.recuperacion.minutos-validez}")
    private long minutosValidez;

    @Value("${app.recuperacion.max-solicitudes-por-hora}")
    private long maxSolicitudesPorHora;

    private static final SecureRandom RANDOM = new SecureRandom();

    @Transactional
    public void solicitar(String email) {
        if (email == null || email.isBlank()) return;

        String emailNormalizado = normalizarEmail(email);

        // 0 resultados: no revelamos nada.
        // Más de uno: el email está duplicado en la base y no se puede determinar
        // la cuenta de forma segura, así que tampoco hacemos nada.
        List<EntidadUsuarios> usuarios = repositorioUsuario
                .findAllByContactoEmail(emailNormalizado);
        if (usuarios.size() != 1) return;

        EntidadUsuarios usuario = usuarios.get(0);

        EntidadCredencial credencial = usuario.getCredencial();
        if (credencial == null || !credencial.isEnabled()) return;

        // Límite de solicitudes por hora, también en silencio
        long recientes = repositorioToken.countByUsuarioAndCreadoEnAfter(
                usuario, LocalDateTime.now().minusHours(1));
        if (recientes >= maxSolicitudesPorHora) return;

        // Un solo token válido a la vez
        repositorioToken.invalidarPendientes(usuario);

        String token = generarToken();
        LocalDateTime ahora = LocalDateTime.now();

        EntidadTokenRecuperacion entidad = new EntidadTokenRecuperacion();
        entidad.setUsuario(usuario);
        entidad.setTokenHash(hash(token));
        entidad.setCreadoEn(ahora);
        entidad.setVencimiento(ahora.plusMinutes(minutosValidez));
        repositorioToken.save(entidad);

        String link = urlFront + "/restablecer?token=" + token;

        // El mail se envía recién cuando la transacción commiteó (ver
        // enviarCorreoRecuperacion). Así un fallo de SMTP no revierte el token
        // ni delata si el email existe.
        eventPublisher.publishEvent(new RecuperacionSolicitadaEvent(
                emailNormalizado, link, minutosValidez));
    }

    @Transactional
    public void restablecer(String token, String nuevaContrasena) {

        EntidadTokenRecuperacion entidad = repositorioToken
                .findByTokenHashForUpdate(hash(token))
                .orElseThrow(() -> new TokenRecuperacionInvalidoException(
                        "El enlace de recuperación no es válido o ya venció."));

        if (entidad.isUsado() || entidad.getVencimiento().isBefore(LocalDateTime.now())) {
            throw new TokenRecuperacionInvalidoException("El enlace de recuperación no es válido o ya venció.");
        }

        EntidadCredencial credencial = entidad.getUsuario().getCredencial();
        if (credencial == null || !credencial.isEnabled()) {
            throw new TokenRecuperacionInvalidoException("El enlace de recuperación no es válido o ya venció.");
        }

        credencial.setPassword(passwordEncoder.encode(nuevaContrasena));
        credencial.setRefreshToken(null); // cierra las sesiones renovables
        credencial.setTokenVersion(credencial.getTokenVersion() + 1); // invalida los access tokens vigentes
        repositorioCredencial.save(credencial);

        entidad.setUsado(true);
        repositorioToken.save(entidad);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void enviarCorreoRecuperacion(RecuperacionSolicitadaEvent evento) {
        try {
            servicioEmail.enviarMail(
                    evento.destinatario(),
                    "Recuperación de contraseña",
                    "Recibimos una solicitud para restablecer tu contraseña.\n\n" +
                            "Ingresá a este enlace para elegir una nueva (vence en " +
                            evento.minutosValidez() + " minutos):\n" +
                            evento.enlace() + "\n\n" +
                            "Si no fuiste vos, ignorá este mensaje.");
        } catch (RuntimeException e) {
            log.error("No se pudo enviar el correo de recuperación a {}",
                    evento.destinatario(), e);
        }
    }

    @Scheduled(cron = "0 0 3 * * *")
    @Transactional
    public void limpiarTokensVencidos() {
        repositorioToken.borrarVencidosAntesDe(LocalDateTime.now());
    }

    private String normalizarEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
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
