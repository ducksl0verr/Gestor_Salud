package com.GrupoProga3.Gestor_Salud.Pago;

import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialDTO;
import com.GrupoProga3.Gestor_Salud.ObraSocial.ServicioObraSocial;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
@AllArgsConstructor
public class ControladorPago {
    private final ServicioPago servicioPago;


    public ResponseEntity<PagoDTO> buscarPorId(Long id) {
        return ResponseEntity.ok(servicioPago.buscarPorId(id));
    }
}