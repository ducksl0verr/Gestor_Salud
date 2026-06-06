package com.GrupoProga3.Gestor_Salud.Proveedores;

import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs.ProveedorActualizar;
import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs.ProveedorNuevo;
import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs.ProveedorRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proveedores")
public class ControladorProveedor {

    private final IServicioProveedor servicioProveedor;

    @PostMapping
    ResponseEntity<ProveedorRespuesta> crear (@RequestBody @Valid ProveedorNuevo nuevo){
        return new ResponseEntity<>(servicioProveedor.crear(nuevo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProveedorRespuesta> buscarPorId (@PathVariable Long id){
        return ResponseEntity.ok(servicioProveedor.buscarPorId(id));
    }

    @GetMapping("/{nombre}")
    ResponseEntity<ProveedorRespuesta> buscarPorNombre (@PathVariable String nombre){
        return ResponseEntity.ok(servicioProveedor.buscarPorNombre(nombre));
    }

    @GetMapping
    ResponseEntity<List<ProveedorRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioProveedor.buscarTodos());
    }

    @PutMapping("/{id}")
    ResponseEntity<ProveedorRespuesta> actualizar (@PathVariable Long id, @RequestBody @Valid ProveedorActualizar nuevo){
        return ResponseEntity.ok(servicioProveedor.actualizar(id,nuevo));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> borrar (@PathVariable Long id){
        servicioProveedor.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
