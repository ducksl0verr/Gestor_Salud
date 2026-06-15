package com.GrupoProga3.Gestor_Salud.features.Usuarios.Controlador;

import com.GrupoProga3.Gestor_Salud.auth.DTOs.CuentaNueva;
import com.GrupoProga3.Gestor_Salud.features.Notificaciones.MensajeDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Servicio.IServicioUsuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class ControladorUsuario {

    private final IServicioUsuario servicioUsuario;

    @GetMapping
    public ResponseEntity<List<UsuarioRespuestaDTO>> buscarTodos ()
    {
        return ResponseEntity.ok(servicioUsuario.buscarTodos());
    }

    @GetMapping("/profesionales")
    public ResponseEntity<List<ProfesionalRespuestaDTO>> buscarTodosProfesionales()
    {
        return ResponseEntity.ok(servicioUsuario.buscarTodosProfesionales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> buscarPorId (@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioUsuario.buscarPorId(id));
    }

    @GetMapping("/profesionales/{id}")
    public ResponseEntity<ProfesionalRespuestaDTO> buscarPorIdProfesionales(@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioUsuario.buscarPorIdProfesional(id));
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('GESTIONAR_USUARIO')")
    public ResponseEntity<UsuarioRespuestaDTO> guardar (@RequestBody @Valid CuentaNueva cuentaNueva)
    {
        return new ResponseEntity<>(servicioUsuario.guardar(cuentaNueva), HttpStatus.CREATED);
    }

    @PostMapping("/profesionales")
    public ResponseEntity<ProfesionalRespuestaDTO> guardarProfesional(@RequestBody @Valid CuentaNueva nueva)
    {
        return new ResponseEntity<>(servicioUsuario.guardarProfesional(nueva),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar (@PathVariable Long id)
    {
        servicioUsuario.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> actualizar(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO)
    {
       return  ResponseEntity.ok(servicioUsuario.actualizar(id, usuarioDTO));
    }

    @PutMapping("/profesionales/{id}")
    public ResponseEntity<ProfesionalRespuestaDTO> actualizarProfesional(@PathVariable Long id, @RequestBody @Valid ProfesionalDTO profesionalDTO)
    {
        return ResponseEntity.ok(servicioUsuario.actualizarProfesional(id,profesionalDTO));
    }

    @PostMapping("/{id}/mensaje")
    ResponseEntity<String> enviarMensaje (@PathVariable Long id, @Valid @RequestBody MensajeDTO mensajeDTO)
    {
        servicioUsuario.enviarMensajeProveedor(id,mensajeDTO);
        return ResponseEntity.ok("Mensaje enviado correctamente.");
    }

}
