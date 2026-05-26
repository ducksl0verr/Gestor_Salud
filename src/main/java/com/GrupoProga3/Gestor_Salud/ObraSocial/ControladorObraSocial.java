package com.GrupoProga3.Gestor_Salud.ObraSocial;

import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class ControladorObraSocial {
    private final ServicioObraSocial servicioObraSocial;

    public ResponseEntity<ObraSocialDTO> buscarPorId (Long id){
        return ResponseEntity.ok(servicioObraSocial.buscarPorId(id));
    }
}
