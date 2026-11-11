package com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO;

import jakarta.validation.constraints.NotBlank;

public record DomicilioNuevo(@NotBlank String calle,
                             @NotBlank String numero,
                              String piso,
                              String depto,
                             @NotBlank String localidad,
                             @NotBlank String provincia,
                             @NotBlank String codigo_postal ){
}
