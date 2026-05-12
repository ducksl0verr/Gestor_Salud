package com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO;

import jakarta.validation.constraints.NotBlank;

public record DomicilioDTO (@NotBlank String calle,
                            @NotBlank String numero,
                            String piso,
                            String depto,
                            String localidad,
                            String provincia,
                            String codigo_postal){
}
