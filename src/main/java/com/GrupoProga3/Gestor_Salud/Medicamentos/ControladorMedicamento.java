package com.GrupoProga3.Gestor_Salud.Medicamentos;

import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoActualizar;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoNuevo;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicamentos")
public class ControladorMedicamento {

    private final IServicioMedicamento servicioMedicamento;

    @PostMapping
    ResponseEntity<MedicamentoRespuesta> crear (@RequestBody @Valid MedicamentoNuevo  medicamentoNuevo){
        return new ResponseEntity<>(servicioMedicamento.crear(medicamentoNuevo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<MedicamentoRespuesta> buscarPorId (@PathVariable Long id){
        return ResponseEntity.ok(servicioMedicamento.buscarPorId(id));
    }

    @GetMapping("/principioActivo/{principio}")
    ResponseEntity<List<MedicamentoRespuesta>>  buscarPorPrincipioActivo (@PathVariable String principio){
        return ResponseEntity.ok(servicioMedicamento.buscarPorPrincipioActivo(principio));
    }

    @GetMapping
    ResponseEntity<List<MedicamentoRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioMedicamento.buscarTodos());
    }

    @PutMapping("/{id}")
    ResponseEntity<MedicamentoRespuesta> actualizar (@PathVariable Long id, @RequestBody @Valid MedicamentoActualizar medicamentoActualizar){
        return ResponseEntity.ok(servicioMedicamento.actualizar(id, medicamentoActualizar));
    }

}
