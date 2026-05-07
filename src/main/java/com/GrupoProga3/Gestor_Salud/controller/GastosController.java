package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.Gastos.GastosDTO;
import com.GrupoProga3.Gestor_Salud.service.GastosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos")
public class GastosController {

    private final GastosService gastosService;

    public GastosController(GastosService gastosService) {
        this.gastosService = gastosService;
    }

    @GetMapping
    public ResponseEntity<List<GastosDTO>> getAll() {
        return ResponseEntity.ok(gastosService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastosDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(gastosService.getById(id));
    }

    @PostMapping
    public ResponseEntity<GastosDTO> create(@Valid @RequestBody GastosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gastosService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastosDTO> update(@PathVariable Long id, @Valid @RequestBody GastosDTO dto) {
        return ResponseEntity.ok(gastosService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gastosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
