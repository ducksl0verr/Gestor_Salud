package com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento;

import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.DTOs.PrescripcionTratamientoNueva;
import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.DTOs.PrescripcionTratamientoRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescripciones")
public class ControladorPrescripcionTratamiento {

    private final IServicioPrescripcionTratamiento servicioPrescripcionTratamiento;

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_TRATAMIENTO')")
    ResponseEntity<PrescripcionTratamientoRespuesta> crear (@RequestBody @Valid PrescripcionTratamientoNueva prescripcionTratamientoNueva){
        return new ResponseEntity<>(servicioPrescripcionTratamiento.crear(prescripcionTratamientoNueva), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_TRATAMIENTO')")
    ResponseEntity<PrescripcionTratamientoRespuesta> buscarPorId (@PathVariable Long id){
        return ResponseEntity.ok(servicioPrescripcionTratamiento.buscarPorId(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_TRATAMIENTO')")
    ResponseEntity<List<PrescripcionTratamientoRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioPrescripcionTratamiento.buscarTodos());
    }

    @PutMapping("/{id}/baja")
    @PreAuthorize("hasAuthority('EDITAR_TRATAMIENTO')")
    ResponseEntity<PrescripcionTratamientoRespuesta> darBaja (@PathVariable Long id){
        return ResponseEntity.ok(servicioPrescripcionTratamiento.pararTratamiento(id));
    }

}
