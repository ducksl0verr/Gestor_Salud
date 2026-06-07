package com.GrupoProga3.Gestor_Salud.Turno;

import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoRespuesta;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;

import java.util.List;

public interface IServicioTurno {
    TurnoRespuesta crear (TurnoNuevo nuevo);
    TurnoRespuesta buscarPorId(Long id);
    List<TurnoRespuesta> buscarTodos();
    TurnoRespuesta actualizar (Long id, TurnoActualizar turno);
    void borrar (Long id);
    List<EntidadTurno> obtenerTurnosFacturables(Long idPaciente);
}
