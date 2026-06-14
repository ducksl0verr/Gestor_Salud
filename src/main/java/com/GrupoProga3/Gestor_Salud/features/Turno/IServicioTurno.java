package com.GrupoProga3.Gestor_Salud.features.Turno;

import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoFacturable;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoRespuesta;

import java.util.List;

public interface IServicioTurno {
    TurnoRespuesta crear (TurnoNuevo nuevo);
    TurnoRespuesta buscarPorId(Long id);
    List<TurnoRespuesta> buscarTodos();
    TurnoRespuesta actualizar (Long id, TurnoActualizar turno);
    void borrar (Long id);
    List<TurnoFacturable> obtenerTurnosFacturables(Long idPaciente);
}
