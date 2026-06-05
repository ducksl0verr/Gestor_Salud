package com.GrupoProga3.Gestor_Salud.Consultorios;

import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs.ConsultorioActualizar;
import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs.ConsultorioNuevo;
import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs.ConsultorioRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultorios")
public class ControladorConsultorios {
    private final IServicioConsultorio servicioConsultorio;

    @PostMapping
    ResponseEntity<ConsultorioRespuesta> crear (@RequestBody @Valid ConsultorioNuevo consultorioNuevo) {
        return new ResponseEntity<>(servicioConsultorio.crear(consultorioNuevo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<ConsultorioRespuesta> buscarPorId (@PathVariable Long id) {
        return ResponseEntity.ok(servicioConsultorio.buscarPorId(id));
    }

    @GetMapping
    ResponseEntity<List<ConsultorioRespuesta>> buscarTodos () {
        return ResponseEntity.ok(servicioConsultorio.buscarTodos());
    }

    @PutMapping("/{id}")
    ResponseEntity<ConsultorioRespuesta> actualizar (@RequestBody @Valid ConsultorioActualizar consultorioActualizar, @PathVariable Long id) {
        return ResponseEntity.ok(servicioConsultorio.actualizar(id, consultorioActualizar));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminar (@PathVariable Long id) {
        servicioConsultorio.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
