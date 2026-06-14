package com.GrupoProga3.Gestor_Salud.features.Pago;

import com.GrupoProga3.Gestor_Salud.features.Pago.DTO.PagoDTO;
import com.GrupoProga3.Gestor_Salud.features.Pago.DTO.PagoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Pago.DTO.PagoRespuesta;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@AllArgsConstructor
public class ControladorPago {

    private final IServicioPago servicioPago;

    @PostMapping
    public ResponseEntity<PagoRespuesta> guardar(
            @Valid @RequestBody PagoNuevo pagoNuevo) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(servicioPago.guardar(pagoNuevo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoRespuesta> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                servicioPago.buscarPorId(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<PagoRespuesta>> buscarTodos() {

        return ResponseEntity.ok(
                servicioPago.buscarTodos()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoRespuesta> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PagoDTO pagoDTO) {

        return ResponseEntity.ok(
                servicioPago.actualizar(id, pagoDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(
            @PathVariable Long id) {

        servicioPago.borrar(id);

        return ResponseEntity.noContent().build();
    }
}

