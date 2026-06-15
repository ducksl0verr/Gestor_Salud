package com.GrupoProga3.Gestor_Salud.features.Gastos;

import com.GrupoProga3.Gestor_Salud.features.Gastos.Dominio.DTOs.GastoActualizar;
import com.GrupoProga3.Gestor_Salud.features.Gastos.Dominio.DTOs.GastoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Gastos.Dominio.DTOs.GastoRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gastos")
public class ControladorGasto {
    private final IServicioGasto servicioGasto;

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_GASTO')")
    ResponseEntity<GastoRespuesta> guardar (@RequestBody @Valid GastoNuevo gastoNuevo) {
        return new ResponseEntity<>(servicioGasto.crear(gastoNuevo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_GASTO')")
    ResponseEntity<GastoRespuesta> buscarPorId (@PathVariable Long id) {
        return ResponseEntity.ok(servicioGasto.buscarPorId(id));
    }

    @GetMapping("/proveedor/{nombre}")
    @PreAuthorize("hasAuthority('VER_GASTO')")
    ResponseEntity<List<GastoRespuesta>> buscarPorProveedor (@PathVariable String nombre) {
        return ResponseEntity.ok(servicioGasto.buscarPorProveedor(nombre));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_GASTO')")
    ResponseEntity<List<GastoRespuesta>> buscarTodos() {
        return ResponseEntity.ok(servicioGasto.buscarTodos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_GASTO')")
    ResponseEntity<GastoRespuesta> actualizar (@PathVariable Long id, @RequestBody @Valid GastoActualizar gastoActualizar) {
        return ResponseEntity.ok(servicioGasto.actualizar(id,gastoActualizar));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ELIMINAR_GASTO')")
    ResponseEntity<GastoRespuesta> borrar (@PathVariable Long id) {
        servicioGasto.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
