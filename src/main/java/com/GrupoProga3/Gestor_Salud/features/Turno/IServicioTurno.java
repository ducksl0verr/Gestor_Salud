package com.GrupoProga3.Gestor_Salud.features.Turno;

import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.DisponibilidadTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoFacturable;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoTurno;

import java.time.LocalDateTime;
import java.util.List;

public interface IServicioTurno {
    TurnoRespuesta crear(TurnoNuevo nuevo);
    TurnoRespuesta buscarPorId(Long id);
    List<TurnoRespuesta> buscarTodos();
    List<TurnoRespuesta> buscarPorProfesional(String username);
    TurnoRespuesta actualizar(Long id, TurnoActualizar turno);
    TurnoRespuesta cambiarEstado(Long id, EstadoTurno estado);
    DisponibilidadTurno verificarDisponibilidad(LocalDateTime fechaHora,
                                                Long idProfesional,
                                                Long idConsultorio,
                                                Long idExcluido);
    void borrar(Long id);
    List<TurnoFacturable> obtenerTurnosFacturables(Long idPaciente);
}
