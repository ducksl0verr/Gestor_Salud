package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.MetodosDePagos.MetodosDePagosDTO;
import com.GrupoProga3.Gestor_Salud.service.MetodosDePagosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodos-de-pagos")
public class MetodosDePagosController {

    private final MetodosDePagosService metodosDePagosService;

    public MetodosDePagosController(MetodosDePagosService metodosDePagosService) {
        this.metodosDePagosService = metodosDePagosService;
    }

    @GetMapping
    public ResponseEntity<List<MetodosDePagosDTO>> getAll() {
        return ResponseEntity.ok(metodosDePagosService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodosDePagosDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(metodosDePagosService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MetodosDePagosDTO> create(@Valid @RequestBody MetodosDePagosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(metodosDePagosService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodosDePagosDTO> update(@PathVariable Long id, @Valid @RequestBody MetodosDePagosDTO dto) {
        return ResponseEntity.ok(metodosDePagosService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        metodosDePagosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
