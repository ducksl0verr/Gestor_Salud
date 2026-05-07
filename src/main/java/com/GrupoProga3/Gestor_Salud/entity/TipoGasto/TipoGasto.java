package com.GrupoProga3.Gestor_Salud.entity.TipoGasto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "Tipo_Gasto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoGasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_gasto")
    private Long idTipoGasto;

    @NotBlank(message = "El nombre del tipo de gasto no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;
}
