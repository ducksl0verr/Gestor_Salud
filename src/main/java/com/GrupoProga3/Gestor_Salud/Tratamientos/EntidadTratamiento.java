package com.GrupoProga3.Gestor_Salud.Tratamientos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "Tratamientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntidadTratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamiento")
    private Long idTratamiento;

    @NotBlank(message = "El nombre del tratamiento no puede estar vacío")
    @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(name = "descripcion", length = 500)
    private String descripcion;
}
