package com.GrupoProga3.Gestor_Salud.Cirugias;

import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs.CirugiaActualizar;
import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs.CirugiaNueva;
import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs.CirugiaRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cirugias")
public class ControlCirugia {
    private final IServicioCirugia servicioCirugia;

    @PostMapping
    ResponseEntity<CirugiaRespuesta> crear (@RequestBody @Valid CirugiaNueva cirugiaNueva) {
        return new ResponseEntity<>(servicioCirugia.crear(cirugiaNueva), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<CirugiaRespuesta> buscarPorId (@PathVariable Long id) {
        return ResponseEntity.ok(servicioCirugia.buscarPorID(id));
    }

    @GetMapping
    ResponseEntity<List<CirugiaRespuesta>> buscarTodos () {
        return ResponseEntity.ok(servicioCirugia.buscarTodos());
    }

    @PutMapping("/{id}")
    ResponseEntity<CirugiaRespuesta> actualizar (@PathVariable Long id, @RequestBody @Valid CirugiaActualizar cirugiaActualizar) {
        return ResponseEntity.ok(servicioCirugia.actualizar(id, cirugiaActualizar));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> borrar (@PathVariable Long id) {
        servicioCirugia.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
