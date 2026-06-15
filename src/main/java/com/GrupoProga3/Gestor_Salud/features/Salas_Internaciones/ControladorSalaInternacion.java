package com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones;

import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs.SalaInternacionActualizar;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs.SalaInternacionNueva;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs.SalaInternacionRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salasInternaciones")
public class ControladorSalaInternacion {
    private final IServicioSalaInternacion servicioSalaInternacion;

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_SALA')")
    ResponseEntity<SalaInternacionRespuesta> crear (@Valid @RequestBody SalaInternacionNueva salaInternacionNueva){
        return new ResponseEntity<>(servicioSalaInternacion.crear(salaInternacionNueva), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_SALA')")
    ResponseEntity<List<SalaInternacionRespuesta>> buscarTodas() {
        return ResponseEntity.ok(servicioSalaInternacion.buscarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_SALA')")
    ResponseEntity<SalaInternacionRespuesta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicioSalaInternacion.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_SALA')")
    ResponseEntity<SalaInternacionRespuesta> actualizar (@PathVariable Long id, @Valid @RequestBody SalaInternacionActualizar salaInternacionActualizar) {
        return ResponseEntity.ok(servicioSalaInternacion.actualizar(id, salaInternacionActualizar));
    }

    @PutMapping("/{idSala}/internar/{idPaciente}")
    @PreAuthorize("hasAuthority('INTERNAR_PACIENTE')")
    ResponseEntity<SalaInternacionRespuesta> internar (@PathVariable Long idSala, @PathVariable Long idPaciente) {
        return ResponseEntity.ok(servicioSalaInternacion.internarPaciente(idSala,idPaciente));
    }
}
