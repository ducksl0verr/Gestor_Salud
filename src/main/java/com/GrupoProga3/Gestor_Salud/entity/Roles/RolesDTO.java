package com.GrupoProga3.Gestor_Salud.entity.Roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolesDTO {

    private Long idRol;

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
}
