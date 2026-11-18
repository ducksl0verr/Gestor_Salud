package com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DomicilioNuevo(
        @NotBlank(message = "La calle es obligatoria")
        @Size(max = 50, message = "La calle no puede superar los 50 caracteres")
        String calle,

        @NotBlank(message = "El número es obligatorio")
        @Size(max = 50, message = "El número no puede superar los 50 caracteres")
        String numero,

        @Size(max = 50, message = "El piso no puede superar los 50 caracteres")
        String piso,

        @Size(max = 50, message = "El departamento no puede superar los 50 caracteres")
        String depto,

        @NotBlank(message = "La localidad es obligatoria")
        @Size(max = 50, message = "La localidad no puede superar los 50 caracteres")
        String localidad,

        @NotBlank(message = "La provincia es obligatoria")
        @Size(max = 50, message = "La provincia no puede superar los 50 caracteres")
        String provincia,

        @NotBlank(message = "El código postal es obligatorio")
        @Size(max = 50, message = "El código postal no puede superar los 50 caracteres")
        String codigo_postal) {
}
