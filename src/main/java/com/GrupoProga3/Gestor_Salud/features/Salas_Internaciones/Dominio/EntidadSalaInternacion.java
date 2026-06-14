package com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio;

import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "salas_internacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntidadSalaInternacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salaInternacion")
    private Long id;

    private Integer capacidad_maxima;

    @OneToMany(mappedBy = "salaInternacion")
    private List<EntidadPaciente> pacientes;

}
