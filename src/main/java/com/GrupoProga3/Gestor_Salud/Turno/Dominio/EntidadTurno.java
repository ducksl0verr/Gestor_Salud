package com.GrupoProga3.Gestor_Salud.Turno.Dominio;

import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.EntidadConsultorio;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="turnos")
public class EntidadTurno {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @Temporal(TemporalType.TIME)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private EstadoFacturacionDeTurno estadoFacturacionDeTurno = EstadoFacturacionDeTurno.NO_FACTURADO;

    @Enumerated(EnumType.STRING)
    private EstadoTurno estadoTurno = EstadoTurno.NO_REALIZADO;
    /// Los siguientes id's deben ser cambiados por instancias de las clases respectivas una vez las tengamos creadas.
    /// La relación es que recibe muchos
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private EntidadPaciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_tratamiento")
    private EntidadTratamiento tratamiento;

    @ManyToOne
    @JoinColumn(name="id_consultorio")
    private EntidadConsultorio consultorio;

    /// Nos tenemos que fijar la relación entre "entidadUsuario" y turno. Creo que deberíamos crear una entidad "profesional"
    /// Momentaneamente, dejo la relacion como un "ManyToOne" para que el programa corra, pero creo que deberia ser
    /// "OneToMany" (un profesional tiene muchos turnos) y no "ManyToOne" (un turno tiene muchos profesionales).
    @ManyToOne
    @JoinColumn(name="id_profesional")
    private EntidadUsuarios profesional;

}
