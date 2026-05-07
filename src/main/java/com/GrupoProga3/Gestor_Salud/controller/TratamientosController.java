package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.Tratamientos.TratamientosDTO;
import com.GrupoProga3.Gestor_Salud.service.TratamientosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientosController {

    private final TratamientosService tratamientosService;

    public TratamientosController(TratamientosService tratamientosService) {
        this.tratamientosService = tratamientosService;
    }

    @GetMapping
    public ResponseEntity<List<TratamientosDTO>> getAll() {
        return ResponseEntity.ok(tratamientosService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamientosDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tratamientosService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TratamientosDTO> create(@Valid @RequestBody TratamientosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamientosService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TratamientosDTO> update(@PathVariable Long id, @Valid @RequestBody TratamientosDTO dto) {
        return ResponseEntity.ok(tratamientosService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tratamientosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
