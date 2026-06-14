package com.GrupoProga3.Gestor_Salud.features.Diagnosticos;

import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs.DiagnosticoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diagnosticos")
public class ControladorDiagnostico {
    private final IServicioDiagnostico servicioDiagnostico;

    @PostMapping
    ResponseEntity<DiagnosticoRespuesta> guardar(@Valid @RequestBody DiagnosticoNuevo diagnosticoNuevo){
        return new ResponseEntity<>(servicioDiagnostico.crear(diagnosticoNuevo), HttpStatus.CREATED);
    }
    @GetMapping
    ResponseEntity<List<DiagnosticoRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioDiagnostico.buscarTodos());
    }
    @GetMapping("/{id}")
    ResponseEntity<DiagnosticoRespuesta> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(servicioDiagnostico.buscarPorId(id));
    }

}
