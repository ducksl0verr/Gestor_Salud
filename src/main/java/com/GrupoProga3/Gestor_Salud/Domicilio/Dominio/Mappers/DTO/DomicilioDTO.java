package com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.Mappers.DTO;

import jakarta.validation.constraints.NotBlank;

public record DomicilioDTO (@NotBlank String calle,
                            @NotBlank String numero,
                            @NotBlank String piso,
                            @NotBlank String depto,
                            @NotBlank String localidad,
                            @NotBlank String provincia,
                            @NotBlank String codigo_postal){
}
