package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.Especialidades.EspecialidadesDTO;
import com.GrupoProga3.Gestor_Salud.service.EspecialidadesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadesController {

    private final EspecialidadesService especialidadesService;

    public EspecialidadesController(EspecialidadesService especialidadesService) {
        this.especialidadesService = especialidadesService;
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadesDTO>> getAll() {
        return ResponseEntity.ok(especialidadesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadesDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(especialidadesService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EspecialidadesDTO> create(@Valid @RequestBody EspecialidadesDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadesService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadesDTO> update(@PathVariable Long id, @Valid @RequestBody EspecialidadesDTO dto) {
        return ResponseEntity.ok(especialidadesService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        especialidadesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
