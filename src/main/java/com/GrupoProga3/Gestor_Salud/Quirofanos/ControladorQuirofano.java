package com.GrupoProga3.Gestor_Salud.Quirofanos;

import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoActualizar;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoNuevo;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoRespuesta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quirofanos")
public class ControladorQuirofano {
    private final IServicioQuirofano servicioQuirofano;

    @PostMapping
    ResponseEntity<QuirofanoRespuesta> crear (@RequestBody QuirofanoNuevo quirofanoNuevo){
        return new ResponseEntity<>(servicioQuirofano.crear(quirofanoNuevo), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<QuirofanoRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioQuirofano.buscarTodos());
    }

    @GetMapping("/{id}")
    ResponseEntity<QuirofanoRespuesta> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(servicioQuirofano.buscarPorId(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<QuirofanoRespuesta> actualizar(@PathVariable Long id, @RequestBody QuirofanoActualizar quirofanoActualizar){
        return ResponseEntity.ok(servicioQuirofano.actualizar(id, quirofanoActualizar));
    }

}
