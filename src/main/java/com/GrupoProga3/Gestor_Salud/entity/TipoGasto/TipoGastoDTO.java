package com.GrupoProga3.Gestor_Salud.entity.TipoGasto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoGastoDTO {

    private Long idTipoGasto;

    @NotBlank(message = "El nombre del tipo de gasto no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
}
