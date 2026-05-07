package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.TipoProvedor.TipoProvedorDTO;
import com.GrupoProga3.Gestor_Salud.service.TipoProvedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-provedor")
public class TipoProvedorController {

    private final TipoProvedorService tipoProvedorService;

    public TipoProvedorController(TipoProvedorService tipoProvedorService) {
        this.tipoProvedorService = tipoProvedorService;
    }

    @GetMapping
    public ResponseEntity<List<TipoProvedorDTO>> getAll() {
        return ResponseEntity.ok(tipoProvedorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProvedorDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoProvedorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TipoProvedorDTO> create(@Valid @RequestBody TipoProvedorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoProvedorService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoProvedorDTO> update(@PathVariable Long id, @Valid @RequestBody TipoProvedorDTO dto) {
        return ResponseEntity.ok(tipoProvedorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoProvedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
