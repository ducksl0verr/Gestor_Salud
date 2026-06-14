package com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.Dominio;

import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
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
    @JoinColumn(name="id_paciente", nullable = false)
    private EntidadPaciente paciente;

    @ManyToOne
    @JoinColumn(name="id_tratamiento", nullable = false)
    private EntidadTratamiento tratamientos;

    @ManyToOne
    @JoinColumn(name="id_profesional")
    private EntidadUsuarios profesional;

    private Boolean activo;

    private LocalDate fecha_inicio;

    private LocalDate fecha_final;
}
