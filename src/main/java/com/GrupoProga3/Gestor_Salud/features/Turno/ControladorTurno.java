package com.GrupoProga3.Gestor_Salud.features.Turno;

import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoFacturable;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CANCELAR_TURNO')")
    ResponseEntity<Void> borrar (@PathVariable Long id) {
        servicioTurno.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
