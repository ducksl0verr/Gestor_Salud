package com.GrupoProga3.Gestor_Salud.Turno.Dominio;

import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Salas.EntidadSala;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.Usuarios.EntidadUsuarios;
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
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    private Time hora;
    @NotBlank
    @Column(length = 100, nullable = false)
    private String estado;
    /// Los siguientes id's deben ser cambiados por instancias de las clases respectivas una vez las tengamos creadas.
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
    /// Nos tenemos que fijar la relación entre "entidadUsuario" y turno. Creo que deberíamos crear una entidad "profesional"
    /// Momentaneamente, dejo la relacion como un "ManyToOne" para que el programa corra, pero creo que deberia ser
    /// "OneToMany" (un profesional tiene muchos turnos) y no "ManyToOne" (un turno tiene muchos profesionales).
    @ManyToOne
    @JoinColumn(name="id_profesional")
    private EntidadUsuarios id_profesional;

}
