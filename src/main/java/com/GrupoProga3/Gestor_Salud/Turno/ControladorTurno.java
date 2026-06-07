package com.GrupoProga3.Gestor_Salud.Turno;

import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/turnos")
public class ControladorTurno {
    private final IServicioTurno servicioTurno;

    @PostMapping
    ResponseEntity<TurnoRespuesta> crear (@Valid @RequestBody TurnoNuevo turno) {
        return new ResponseEntity<>(servicioTurno.crear(turno), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<TurnoRespuesta>> buscarTodos () {
        return new ResponseEntity<>(servicioTurno.buscarTodos(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    ResponseEntity<TurnoRespuesta> buscarPorId (@PathVariable Long id) {
        return new ResponseEntity<>(servicioTurno.buscarPorId(id), HttpStatus.FOUND);
    }

    @GetMapping("/pacientes/{id}/turnos-facturables")
    public ResponseEntity<?> obtenerTurnosFacturables(@PathVariable Long id) {

        return ResponseEntity.ok(servicioTurno.obtenerTurnosFacturables(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<TurnoRespuesta> actualizar (@Valid @RequestBody TurnoActualizar actualizacion, @PathVariable Long id) {
        return ResponseEntity.ok(servicioTurno.actualizar(id, actualizacion));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> borrar (@PathVariable Long id) {
        servicioTurno.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
