package com.GrupoProga3.Gestor_Salud.auth;

import com.GrupoProga3.Gestor_Salud.auth.DTOs.AuthPedido;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.AuthRespuesta;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.CuentaNueva;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.RefreshTokenPedido;
import com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion.DTOS.OlvideContrasenaPedido;
import com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion.DTOS.RestablecerContrasenaPedido;
import com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion.ServicioRecuperacion;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Servicio.ServicioUsuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ControladorAuth {

    private final ServicioAuth servicioAuth;
    private final ServicioUsuario servicioUsuario;
    private final ServicioRecuperacion servicioRecuperacion;

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

    @PostMapping("/olvide-contrasena")
    public ResponseEntity<String> olvideContrasena (@RequestBody @Valid OlvideContrasenaPedido pedido){
        servicioRecuperacion.solicitar(pedido.email());
        return ResponseEntity.ok("Si el email está registrado, te enviamos un enlace para restablecer la contraseña");
    }

    @PostMapping("/restablecer-contrasena")
    public ResponseEntity<String> restablecerContrasena(
            @RequestBody @Valid RestablecerContrasenaPedido pedido) {
        servicioRecuperacion.restablecer(pedido.token(), pedido.nuevaContrasena());
        return ResponseEntity.ok("Contraseña actualizada correctamente.");
    }

}
