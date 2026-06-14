package com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento;

import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.Dominio.DTOs.PrescripcionTratamientoNueva;
import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.Dominio.DTOs.PrescripcionTratamientoRespuesta;

import java.util.List;

public interface IServicioPrescripcionTratamiento {
    PrescripcionTratamientoRespuesta crear (PrescripcionTratamientoNueva prescripcionTratamiento);
    PrescripcionTratamientoRespuesta buscarPorId(Long id);
    List<PrescripcionTratamientoRespuesta> buscarTodos();
    PrescripcionTratamientoRespuesta pararTratamiento(Long id);
}
