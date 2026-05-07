package com.GrupoProga3.Gestor_Salud.entity.Diagnosticos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "Diagnosticos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosticos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diagnostico")
    private Long idDiagnostico;

    @NotBlank(message = "El nombre del diagnóstico no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(name = "descripcion", length = 500)
    private String descripcion;
}
