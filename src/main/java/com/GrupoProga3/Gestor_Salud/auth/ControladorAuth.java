package com.GrupoProga3.Gestor_Salud.auth;

import com.GrupoProga3.Gestor_Salud.auth.DTOs.AuthPedido;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.AuthRespuesta;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.CuentaNueva;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.RefreshTokenPedido;
import com.GrupoProga3.Gestor_Salud.auth.credenciales.EntidadCredencial;
import com.GrupoProga3.Gestor_Salud.auth.credenciales.RepositorioCredencial;
import com.GrupoProga3.Gestor_Salud.auth.jwt.ServicioJWT;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Servicio.ServicioUsuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ControladorAuth {

    private final ServicioAuth servicioAuth;
    private final ServicioUsuario servicioUsuario;

    @PostMapping("/iniciar")
    public ResponseEntity<AuthRespuesta> authenticateUser(@RequestBody @Valid AuthPedido authPedido) {
        return ResponseEntity.ok(servicioAuth.authenticate(authPedido));
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioRespuestaDTO> registrarUsuario(@RequestBody @Valid CuentaNueva nueva) {
        return new ResponseEntity<>(servicioUsuario.guardar(nueva), HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthRespuesta> refreshToken(@RequestBody @Valid
                                                      RefreshTokenPedido request){
        AuthRespuesta response =
                servicioAuth.refreshAccessToken(request.refreshToken());
        return ResponseEntity.ok(response);
    }

}
