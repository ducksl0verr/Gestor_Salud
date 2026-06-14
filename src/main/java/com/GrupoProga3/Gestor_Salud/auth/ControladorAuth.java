package com.GrupoProga3.Gestor_Salud.auth;

import com.GrupoProga3.Gestor_Salud.auth.DTOs.AuthPedido;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.AuthRespuesta;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.CuentaNueva;
import com.GrupoProga3.Gestor_Salud.auth.DTOs.RefreshTokenPedido;
import com.GrupoProga3.Gestor_Salud.auth.jwt.ServicioJWT;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Servicio.ServicioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ControladorAuth {

    private final ServicioAuth servicioAuth;
    private final ServicioUsuario servicioUsuario;
    private final ServicioJWT servicioJWT;

    @PostMapping("/inicio")
    public ResponseEntity<AuthRespuesta> authnticateUser(@RequestBody AuthPedido authPedido) {
        return ResponseEntity.ok(servicioAuth.authenticate(authPedido));
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioRespuestaDTO> registrarUsuario(@RequestBody CuentaNueva nueva) {
        return new ResponseEntity<>(servicioUsuario.guardar(nueva), HttpStatus.CREATED);
    }

  /*  @PostMapping()
    public ResponseEntity<AuthRespuesta> authenticateUser(@RequestBody
                                                         AuthPedido authRequest){
        EntidadCredencial user = servicioAuth.authenticate(authRequest);
        System.out.println(user);
        String token = servicioJWT.generateToken(user);
        System.out.println(token);
        return ResponseEntity.ok(new AuthRespuesta(token,
                user.getRefreshToken()));
    }

   */
    @PostMapping("/refresh")
    public ResponseEntity<AuthRespuesta> refreshToken(@RequestBody
                                                      RefreshTokenPedido request){
        AuthRespuesta response =
                servicioAuth.refreshAccessToken(request.refreshToken());
        return ResponseEntity.ok(response);
    }

}
