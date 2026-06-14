package com.GrupoProga3.Gestor_Salud.ObraSocial;


import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialNueva;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialRespuesta;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obras-sociales")
@RequiredArgsConstructor
public class ControladorObraSocial {

    private final ServicioObraSocial servicioObraSocial;

    @PostMapping
    public ResponseEntity<ObraSocialRespuesta> guardar(
            @Valid @RequestBody ObraSocialNueva nueva) {

        ObraSocialRespuesta respuesta = servicioObraSocial.guardar(nueva);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<ObraSocialRespuesta>> buscarObraSocial(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String codigo) {

        return ResponseEntity.ok(
                servicioObraSocial.buscarObraSocial(nombre, codigo)
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ObraSocialRespuesta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicioObraSocial.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        servicioObraSocial.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
