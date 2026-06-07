package com.GrupoProga3.Gestor_Salud.Salas_Internaciones;

import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionActualizar;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionNueva;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionRespuesta;

import java.util.List;

public interface IServicioSalaInternacion {
    SalaInternacionRespuesta crear (SalaInternacionNueva salaInternacionNueva);
    SalaInternacionRespuesta actualizar (Long id, SalaInternacionActualizar salaInternacionActualizar);
    SalaInternacionRespuesta buscarPorId(Long id);
    List<SalaInternacionRespuesta> buscarTodos();

}
