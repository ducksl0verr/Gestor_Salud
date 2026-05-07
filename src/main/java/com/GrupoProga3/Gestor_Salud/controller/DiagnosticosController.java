package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.Diagnosticos.domain.DiagnosticosDTO;
import com.GrupoProga3.Gestor_Salud.entity.Diagnosticos.DiagnosticosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticosController {

    private final DiagnosticosService diagnosticosService;

    public DiagnosticosController(DiagnosticosService diagnosticosService) {
        this.diagnosticosService = diagnosticosService;
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticosDTO>> getAll() {
        return ResponseEntity.ok(diagnosticosService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticosDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosticosService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DiagnosticosDTO> create(@Valid @RequestBody DiagnosticosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnosticosService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticosDTO> update(@PathVariable Long id, @Valid @RequestBody DiagnosticosDTO dto) {
        return ResponseEntity.ok(diagnosticosService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        diagnosticosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
