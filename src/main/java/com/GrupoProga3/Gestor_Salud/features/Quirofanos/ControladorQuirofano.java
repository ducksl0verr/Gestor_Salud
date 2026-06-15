package com.GrupoProga3.Gestor_Salud.features.Quirofanos;

import com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.DTOs.QuirofanoActualizar;
import com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.DTOs.QuirofanoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.DTOs.QuirofanoRespuesta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quirofanos")
public class ControladorQuirofano {
    private final IServicioQuirofano servicioQuirofano;

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_SALA')")
    ResponseEntity<QuirofanoRespuesta> crear (@RequestBody QuirofanoNuevo quirofanoNuevo){
        return new ResponseEntity<>(servicioQuirofano.crear(quirofanoNuevo), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_SALA')")
    ResponseEntity<List<QuirofanoRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioQuirofano.buscarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_SALA')")
    ResponseEntity<QuirofanoRespuesta> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(servicioQuirofano.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_SALA')")
    ResponseEntity<QuirofanoRespuesta> actualizar(@PathVariable Long id, @RequestBody QuirofanoActualizar quirofanoActualizar){
        return ResponseEntity.ok(servicioQuirofano.actualizar(id, quirofanoActualizar));
    }

    @PutMapping("/{id}/ocupar")
    @PreAuthorize("hasAuthority('EDITAR_SALA')")
    ResponseEntity<QuirofanoRespuesta> ocuparQuirofano (@PathVariable Long id){
        return ResponseEntity.ok(servicioQuirofano.ocuparQuirofano(id));
    }

}
