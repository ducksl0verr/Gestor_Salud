package com.GrupoProga3.Gestor_Salud.features.Proveedores;

import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs.ProveedorActualizar;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs.ProveedorNuevo;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs.ProveedorRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/proveedores")
public class ControladorProveedor {

    private final IServicioProveedor servicioProveedor;

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_PROVEEDOR')")
    ResponseEntity<ProveedorRespuesta> crear (@RequestBody @Valid ProveedorNuevo nuevo){
        return new ResponseEntity<>(servicioProveedor.crear(nuevo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_PROVEEDOR')")
    ResponseEntity<ProveedorRespuesta> buscarPorId (@PathVariable Long id){
        return ResponseEntity.ok(servicioProveedor.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    @PreAuthorize("hasAuthority('VER_PROVEEDOR')")
    ResponseEntity<ProveedorRespuesta> buscarPorNombre (@PathVariable String nombre){
        return ResponseEntity.ok(servicioProveedor.buscarPorNombre(nombre));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_PROVEEDOR')")
    ResponseEntity<List<ProveedorRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioProveedor.buscarTodos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_PROVEEDOR')")
    ResponseEntity<ProveedorRespuesta> actualizar (@PathVariable Long id, @RequestBody @Valid ProveedorActualizar nuevo){
        return ResponseEntity.ok(servicioProveedor.actualizar(id,nuevo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ELIMINAR_PROVEEDOR')")
    ResponseEntity<Void> borrar (@PathVariable Long id){
        servicioProveedor.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
