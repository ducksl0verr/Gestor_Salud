package com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento;

import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.EntidadTratamiento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="prescripcion_tratamiento")
public class EntidadPrescripcionTratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private EntidadPaciente pacientes;

    @ManyToOne
    private EntidadTratamiento tratamientos;

    private Boolean activo;

    private LocalDate fecha_inicio;

    private LocalDate fecha_final;
}
