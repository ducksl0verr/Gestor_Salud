package com.GrupoProga3.Gestor_Salud.Turno;

import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoFacturable;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoRespuesta;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;

import java.util.List;

public interface IServicioTurno {
    TurnoRespuesta crear (TurnoNuevo nuevo);
    TurnoRespuesta buscarPorId(Long id);
    List<TurnoRespuesta> buscarTodos();
    TurnoRespuesta actualizar (Long id, TurnoActualizar turno);
    void borrar (Long id);
    List<TurnoFacturable> obtenerTurnosFacturables(Long idPaciente);
}
