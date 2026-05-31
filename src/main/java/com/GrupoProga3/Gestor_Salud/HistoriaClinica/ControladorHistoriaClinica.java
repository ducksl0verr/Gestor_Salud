package com.GrupoProga3.Gestor_Salud.HistoriaClinica;

import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaActualizar;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaNueva;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaRespuesta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/historiaClinica")
public class ControladorHistoriaClinica {
    private final IServicioHistoriaClinica servicioHistoriaClinica;
    @PostMapping
    ResponseEntity<HistoriaClinicaRespuesta> crear (HistoriaClinicaNueva historiaClinica){
        return new ResponseEntity<>(servicioHistoriaClinica.crear(historiaClinica), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    ResponseEntity<HistoriaClinicaRespuesta> buscarPorId (@PathVariable Long id){
        return new ResponseEntity<>(servicioHistoriaClinica.buscarPorId(id), HttpStatus.FOUND);
    }
    @GetMapping
    ResponseEntity<List<HistoriaClinicaRespuesta>> buscarTodos(){
        return new ResponseEntity<>(servicioHistoriaClinica.buscarTodos(), HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    ResponseEntity<HistoriaClinicaRespuesta> actualizar  (@PathVariable Long id, HistoriaClinicaActualizar actualizacion){
        return ResponseEntity.ok(servicioHistoriaClinica.actualizar(id, actualizacion));
    }
}
