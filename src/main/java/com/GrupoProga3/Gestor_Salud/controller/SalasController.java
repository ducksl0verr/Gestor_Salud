package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.Salas.SalasDTO;
import com.GrupoProga3.Gestor_Salud.service.SalasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalasController {

    private final SalasService salasService;

    public SalasController(SalasService salasService) {
        this.salasService = salasService;
    }

    @GetMapping
    public ResponseEntity<List<SalasDTO>> getAll() {
        return ResponseEntity.ok(salasService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalasDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(salasService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SalasDTO> create(@Valid @RequestBody SalasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salasService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalasDTO> update(@PathVariable Long id, @Valid @RequestBody SalasDTO dto) {
        return ResponseEntity.ok(salasService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        salasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
