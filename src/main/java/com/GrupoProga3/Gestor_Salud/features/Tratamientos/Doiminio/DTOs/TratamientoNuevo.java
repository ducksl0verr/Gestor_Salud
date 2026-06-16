package com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TratamientoNuevo (@NotBlank(message = "El nombre del tratamiento no puede estar vacío")
                                @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
                                String nombre,
                                @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
                                String descripcion,
                                @NotNull
                                @Positive(message = "El precio debe ser mayor que cero.")
                                BigDecimal precio) {
}
