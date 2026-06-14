package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio;

import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.EntidadConsultorio;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
