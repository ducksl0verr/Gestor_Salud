package com.GrupoProga3.Gestor_Salud.Turno;

import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositorioTurno extends JpaRepository<EntidadTurno, Long> {
    List<EntidadTurno> findByPacienteIdAndEstadoFacturacionDeTurnoAndEstadoTurno(Long idPaciente, EstadoFacturacionDeTurno estadoFacturacion, EstadoTurno estadoTurno);
    List<EntidadTurno> findByFechaAndEstadoTurno(LocalDate fecha, EstadoTurno estado);
}
