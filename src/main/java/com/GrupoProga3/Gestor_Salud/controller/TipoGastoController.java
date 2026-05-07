package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.TipoGasto.TipoGastoDTO;
import com.GrupoProga3.Gestor_Salud.service.TipoGastoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-gasto")
public class TipoGastoController {

    private final TipoGastoService tipoGastoService;

    public TipoGastoController(TipoGastoService tipoGastoService) {
        this.tipoGastoService = tipoGastoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoGastoDTO>> getAll() {
        return ResponseEntity.ok(tipoGastoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoGastoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoGastoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TipoGastoDTO> create(@Valid @RequestBody TipoGastoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoGastoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoGastoDTO> update(@PathVariable Long id, @Valid @RequestBody TipoGastoDTO dto) {
        return ResponseEntity.ok(tipoGastoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoGastoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
