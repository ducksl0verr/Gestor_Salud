package com.GrupoProga3.Gestor_Salud.entity.Salas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalasDTO {

    private Long idSalas;

    @NotBlank(message = "El nombre de la sala no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
}
