package com.GrupoProga3.Gestor_Salud.DetalleFacturas.Controlador;

import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Dominio.DTO.DetalleFacturaDTO;
import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Servicio.IServicioDetalleFactura;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/detalleFacturas")
public class ControladorDetalleFacturas {

    private final IServicioDetalleFactura servicioDetalleFactura;

    @PostMapping
    public ResponseEntity<DetalleFacturaDTO> guardar(@RequestBody @Valid DetalleFacturaDTO detalleFacturaDTO)
    {
        return new ResponseEntity<>(servicioDetalleFactura.guardar(detalleFacturaDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<DetalleFacturaDTO>> buscarTodos()
    {
        return ResponseEntity.ok(servicioDetalleFactura.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFacturaDTO> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioDetalleFactura.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleFacturaDTO> actualizar (@PathVariable Long id, @RequestBody @Valid DetalleFacturaDTO detalleFacturaDTO)
    {
        return ResponseEntity.ok(servicioDetalleFactura.actualizar(id,detalleFacturaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleFacturaDTO> borrar(@PathVariable Long id)
    {
        servicioDetalleFactura.borrar(id);
        return ResponseEntity.noContent().build();
    }



}
