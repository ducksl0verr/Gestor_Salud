package com.GrupoProga3.Gestor_Salud.Recetas;

import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.DTOs.RecetaNueva;
import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.DTOs.RecetaRespuesta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recetas")
public class ControladorReceta {
    private final IServicioReceta servicioReceta;

    @PostMapping
    ResponseEntity<RecetaRespuesta> guardar (@RequestBody RecetaNueva receta) {
        return new ResponseEntity<>(servicioReceta.crear(receta), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<RecetaRespuesta> buscarPorId (@PathVariable Long id) {
        return ResponseEntity.ok(servicioReceta.buscarPorId(id));
    }

    @GetMapping
    ResponseEntity<List<RecetaRespuesta>> buscarTodos() {
        return ResponseEntity.ok(servicioReceta.buscarTodos());
    }

    @GetMapping("/pacientes/{id}")
    ResponseEntity<List<RecetaRespuesta>> buscarPorPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(servicioReceta.buscarPorPaciente(id));
    }
}
