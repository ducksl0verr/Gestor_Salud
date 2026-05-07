package com.GrupoProga3.Gestor_Salud.entity.Especialidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "Especialidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Especialidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Long idEspecialidad;

    @NotBlank(message = "El nombre de la especialidad no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;
}