package com.GrupoProga3.Gestor_Salud.entity.Tratamientos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TratamientosDTO {

    private Long idTratamiento;

    @NotBlank(message = "El nombre del tratamiento no puede estar vacío")
    @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;
}
