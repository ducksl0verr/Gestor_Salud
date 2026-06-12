package com.GrupoProga3.Gestor_Salud.HistoriaClinica;

import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaActualizar;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaNueva;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/historiasClinicas")
public class ControladorHistoriaClinica {
    private final IServicioHistoriaClinica servicioHistoriaClinica;
    @PostMapping
    ResponseEntity<HistoriaClinicaRespuesta> crear (@Valid @RequestBody HistoriaClinicaNueva historiaClinica){
        return new ResponseEntity<>(servicioHistoriaClinica.crear(historiaClinica), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    ResponseEntity<HistoriaClinicaRespuesta> buscarPorId (@PathVariable Long id){
        return ResponseEntity.ok(servicioHistoriaClinica.buscarPorId(id));
    }
    @GetMapping
    ResponseEntity<List<HistoriaClinicaRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioHistoriaClinica.buscarTodos());
    }
    @PutMapping("/{id}")
    ResponseEntity<HistoriaClinicaRespuesta> actualizar  (@PathVariable Long id, @Valid @RequestBody HistoriaClinicaActualizar actualizacion){
        return ResponseEntity.ok(servicioHistoriaClinica.actualizar(id, actualizacion));
    }
}
