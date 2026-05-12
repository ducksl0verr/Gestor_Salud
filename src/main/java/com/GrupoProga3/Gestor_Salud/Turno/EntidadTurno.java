package com.GrupoProga3.Gestor_Salud.Turno;

import com.GrupoProga3.Gestor_Salud.Pacientes.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Salas.EntidadSala;
import com.GrupoProga3.Gestor_Salud.Tratamientos.EntidadTratamiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Turnos")
public class EntidadTurno {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_turno;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    private Time hora;
    @NotBlank
    @Column(length = 100, nullable = false)
    private String estado;
    /// Los sigueintes id's deben ser cambiados por intancias de las clases respectivas una vez las tengamos creadas.
    /// La relación es que recibe muchos
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private EntidadPaciente id_paciente;
    @ManyToOne
    @JoinColumn(name = "id_tratamiento")
    private EntidadTratamiento id_tratamiento;
    @ManyToOne
    @JoinColumn(name="id_sala")
    private EntidadSala id_sala;
    private int id_profesional;

}
