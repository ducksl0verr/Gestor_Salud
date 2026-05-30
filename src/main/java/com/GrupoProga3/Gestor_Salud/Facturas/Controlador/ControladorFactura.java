package com.GrupoProga3.Gestor_Salud.Facturas.Controlador;

import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaDTO;
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

    @PostMapping
    public ResponseEntity<FacturaDTO> guardar (@RequestBody @Valid FacturaDTO facturaDTO)
    {
        return new ResponseEntity<>(servicioFactura.guardar(facturaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> buscarTodos()
    {
        return ResponseEntity.ok(servicioFactura.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioFactura.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> actualizar (@PathVariable Long id, @RequestBody @Valid FacturaDTO facturaDTO)
    {
        return ResponseEntity.ok(servicioFactura.actualizar(id,facturaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar (@PathVariable Long id)
    {
        servicioFactura.borrar(id);
        return ResponseEntity.noContent().build();
    }

}
