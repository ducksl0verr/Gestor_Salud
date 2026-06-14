package com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioRespuesta;

import java.util.List;

public record ObraSocialRespuesta(

        Long id,
        String nombre,
        String cobertura,
        List<DomicilioRespuesta> domicilios

) {
}