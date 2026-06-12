package com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContactoNuevo(

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres")
        String apellido,

        @NotBlank(message = "El teléfono no puede estar vacío")
        String telefono,

        @NotNull(message = "Debe indicar un paciente")
        Long idPaciente

) {
}
