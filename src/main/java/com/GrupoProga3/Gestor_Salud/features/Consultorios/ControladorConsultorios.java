package com.GrupoProga3.Gestor_Salud.features.Consultorios;

import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioActualizar;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consultorios")
public class ControladorConsultorios {
    private final IServicioConsultorio servicioConsultorio;

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_SALA')")
    ResponseEntity<ConsultorioRespuesta> crear (@RequestBody @Valid ConsultorioNuevo consultorioNuevo) {
        return new ResponseEntity<>(servicioConsultorio.crear(consultorioNuevo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_SALA')")
    ResponseEntity<ConsultorioRespuesta> buscarPorId (@PathVariable Long id) {
        return ResponseEntity.ok(servicioConsultorio.buscarPorId(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_SALA')")
    ResponseEntity<List<ConsultorioRespuesta>> buscarTodos () {
        return ResponseEntity.ok(servicioConsultorio.buscarTodos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_SALA')")
    ResponseEntity<ConsultorioRespuesta> actualizar (@RequestBody @Valid ConsultorioActualizar consultorioActualizar, @PathVariable Long id) {
        return ResponseEntity.ok(servicioConsultorio.actualizar(id, consultorioActualizar));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ELIMINAR_SALA')")
    ResponseEntity<Void> eliminar (@PathVariable Long id) {
        servicioConsultorio.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
