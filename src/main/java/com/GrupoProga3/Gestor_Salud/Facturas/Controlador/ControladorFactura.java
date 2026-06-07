package com.GrupoProga3.Gestor_Salud.Facturas.Controlador;

import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaNueva;
import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaRespuesta;
import com.GrupoProga3.Gestor_Salud.Facturas.Servicio.IServicioFactura;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facturas")
public class ControladorFactura {

    private final IServicioFactura servicioFactura;

    @GetMapping
    public ResponseEntity<List<FacturaRespuesta>> buscarTodos()
    {
        return ResponseEntity.ok(servicioFactura.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaRespuesta> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioFactura.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> crearFactura(@RequestBody @Valid FacturaNueva facturaNueva){

        return ResponseEntity.status(HttpStatus.CREATED).body(servicioFactura.crearFactura(facturaNueva));
    }

}
