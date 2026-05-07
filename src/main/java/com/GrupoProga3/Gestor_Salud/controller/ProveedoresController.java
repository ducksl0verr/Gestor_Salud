package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.Proveedores.ProveedoresDTO;
import com.GrupoProga3.Gestor_Salud.service.ProveedoresService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedoresController {

    private final ProveedoresService proveedoresService;

    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedoresDTO>> getAll() {
        return ResponseEntity.ok(proveedoresService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedoresDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(proveedoresService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProveedoresDTO> create(@Valid @RequestBody ProveedoresDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedoresService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedoresDTO> update(@PathVariable Long id, @Valid @RequestBody ProveedoresDTO dto) {
        return ResponseEntity.ok(proveedoresService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proveedoresService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
