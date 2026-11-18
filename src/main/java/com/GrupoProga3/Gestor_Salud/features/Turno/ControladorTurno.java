package com.GrupoProga3.Gestor_Salud.features.Turno;

import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.CambioEstadoTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.DisponibilidadTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoFacturable;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/turnos")
public class ControladorTurno {
    private final IServicioTurno servicioTurno;

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_TURNO')")
    ResponseEntity<TurnoRespuesta> crear (@Valid @RequestBody TurnoNuevo turno) {
        return new ResponseEntity<>(servicioTurno.crear(turno), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_TURNO')")
    ResponseEntity<List<TurnoRespuesta>> buscarTodos () {
        return ResponseEntity.ok(servicioTurno.buscarTodos());
    }

    @GetMapping("/mis-turnos")
    @PreAuthorize("hasAuthority('VER_TURNO')")
    ResponseEntity<List<TurnoRespuesta>> buscarMisTurnos (Authentication authentication) {
        return ResponseEntity.ok(servicioTurno.buscarPorProfesional(authentication.getName()));
    }

    // Consulta usada por el formulario para advertir solapes antes de registrar.
    @GetMapping("/disponibilidad")
    @PreAuthorize("hasAuthority('VER_TURNO')")
    ResponseEntity<DisponibilidadTurno> verificarDisponibilidad(
            @RequestParam LocalDateTime fechaHora,
            @RequestParam Long idProfesional,
            @RequestParam Long idConsultorio,
            @RequestParam(required = false) Long idExcluido) {
        return ResponseEntity.ok(
                servicioTurno.verificarDisponibilidad(fechaHora, idProfesional, idConsultorio, idExcluido)
        );
    }

    @GetMapping("/{id:\\d+}")
    @PreAuthorize("hasAuthority('VER_TURNO')")
    ResponseEntity<TurnoRespuesta> buscarPorId (@PathVariable Long id) {
        return ResponseEntity.ok(servicioTurno.buscarPorId(id));
    }

    @GetMapping("/pacientes/{id}/turnos-facturables")
    @PreAuthorize("hasAuthority('VER_TURNO_FACTURABLE')")
    public ResponseEntity<List<TurnoFacturable>> obtenerTurnosFacturables(@PathVariable Long id) {

        return ResponseEntity.ok(servicioTurno.obtenerTurnosFacturables(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_TURNO')")
    ResponseEntity<TurnoRespuesta> actualizar (@Valid @RequestBody TurnoActualizar actualizacion, @PathVariable Long id) {
        return ResponseEntity.ok(servicioTurno.actualizar(id, actualizacion));
    }

    // El perfil profesional solo cambia el estado; no edita fecha ni datos del turno.
    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasAuthority('CANCELAR_TURNO')")
    ResponseEntity<TurnoRespuesta> cambiarEstado (@PathVariable Long id,
                                                  @Valid @RequestBody CambioEstadoTurno cambio) {
        return ResponseEntity.ok(servicioTurno.cambiarEstado(id, cambio.estado()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CANCELAR_TURNO')")
    ResponseEntity<Void> borrar (@PathVariable Long id) {
        servicioTurno.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
