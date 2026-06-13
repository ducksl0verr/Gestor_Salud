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
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private EstadoFacturacionDeTurno estadoFacturacionDeTurno;

    @Enumerated(EnumType.STRING)
    private EstadoTurno estadoTurno;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private EntidadPaciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_tratamiento")
    private EntidadTratamiento tratamiento;

    @ManyToOne
    @JoinColumn(name="id_consultorio")
    private EntidadConsultorio consultorio;

    @ManyToOne
    @JoinColumn(name="id_profesional")
    private EntidadUsuarios profesional;

}
