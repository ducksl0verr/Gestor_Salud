package com.GrupoProga3.Gestor_Salud.features.Cirugias;

import com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.DTOs.CirugiaActualizar;
import com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.DTOs.CirugiaNueva;
import com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.DTOs.CirugiaRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cirugias")
public class ControlCirugia {
    private final IServicioCirugia servicioCirugia;

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_CIRUGIA')")
    ResponseEntity<CirugiaRespuesta> crear (@RequestBody @Valid CirugiaNueva cirugiaNueva) {
        return new ResponseEntity<>(servicioCirugia.crear(cirugiaNueva), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_SALA')")
    ResponseEntity<CirugiaRespuesta> buscarPorId (@PathVariable Long id) {
        return ResponseEntity.ok(servicioCirugia.buscarPorID(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_SALA')")
    ResponseEntity<List<CirugiaRespuesta>> buscarTodos () {
        return ResponseEntity.ok(servicioCirugia.buscarTodos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_SALA')")
    ResponseEntity<CirugiaRespuesta> actualizar (@PathVariable Long id, @RequestBody @Valid CirugiaActualizar cirugiaActualizar) {
        return ResponseEntity.ok(servicioCirugia.actualizar(id, cirugiaActualizar));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ELIMINAR_SALA')")
    ResponseEntity<Void> borrar (@PathVariable Long id) {
        servicioCirugia.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
