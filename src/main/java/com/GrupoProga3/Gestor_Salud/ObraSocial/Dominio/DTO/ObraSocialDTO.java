package com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO;

import jakarta.validation.constraints.NotBlank;


public record ObraSocialDTO
        (@NotBlank String nombre,
    @NotBlank String cobertura){}

