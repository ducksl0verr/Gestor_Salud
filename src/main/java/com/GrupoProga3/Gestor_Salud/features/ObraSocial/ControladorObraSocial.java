package com.GrupoProga3.Gestor_Salud.features.ObraSocial;


import com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.DTO.ObraSocialDTO;
import com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.DTO.ObraSocialNueva;
import com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.DTO.ObraSocialRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obras-sociales")
@RequiredArgsConstructor
public class ControladorObraSocial {

    private final ServicioObraSocial servicioObraSocial;

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_OBRA_SOCIAL')")
    public ResponseEntity<ObraSocialRespuesta> guardar(
            @Valid @RequestBody ObraSocialNueva nueva) {

        ObraSocialRespuesta respuesta = servicioObraSocial.guardar(nueva);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_OBRA_SOCIAL')")
    public ResponseEntity<List<ObraSocialRespuesta>> buscarObraSocial(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String codigo) {

        return ResponseEntity.ok(
                servicioObraSocial.buscarObraSocial(nom, codigo)
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_OBRA_SOCIAL')")
    ResponseEntity<ObraSocialRespuesta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicioObraSocial.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_OBRA_SOCIAL')")
    ResponseEntity<ObraSocialRespuesta> actualizar(@PathVariable Long id, @RequestBody @Valid ObraSocialDTO dto) {
        return ResponseEntity.ok(servicioObraSocial.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ELIMINAR_OBRA_SOCIAL')")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        servicioObraSocial.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
