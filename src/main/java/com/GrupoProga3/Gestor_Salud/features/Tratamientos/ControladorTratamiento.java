package com.GrupoProga3.Gestor_Salud.features.Tratamientos;

import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.DTOs.TratamientoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.DTOs.TratamientoRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tratamientos")
public class ControladorTratamiento {
    private final IServicioTratamiento servicioTratamiento;

    @PostMapping
    ResponseEntity<TratamientoRespuesta> crear (@Valid @RequestBody TratamientoNuevo tratamientoNuevo) {
        return new ResponseEntity<>(servicioTratamiento.crear(tratamientoNuevo), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<TratamientoRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioTratamiento.buscarTodos());
    }

    @GetMapping("/{id}")
    ResponseEntity<TratamientoRespuesta> buscarPorId (@PathVariable Long id){
        return ResponseEntity.ok(servicioTratamiento.buscarPorId(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<TratamientoRespuesta> actualizar (@PathVariable Long id, @Valid @RequestBody TratamientoNuevo tratamientoNuevo) {
        return ResponseEntity.ok(servicioTratamiento.actualizar(id, tratamientoNuevo));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminar (@PathVariable Long id){
        servicioTratamiento.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
