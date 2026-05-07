package com.GrupoProga3.Gestor_Salud.controller;

import com.GrupoProga3.Gestor_Salud.entity.Roles.RolesDTO;
import com.GrupoProga3.Gestor_Salud.service.RolesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public ResponseEntity<List<RolesDTO>> getAll() {
        return ResponseEntity.ok(rolesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(rolesService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RolesDTO> create(@Valid @RequestBody RolesDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rolesService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesDTO> update(@PathVariable Long id, @Valid @RequestBody RolesDTO dto) {
        return ResponseEntity.ok(rolesService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
