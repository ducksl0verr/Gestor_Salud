package com.GrupoProga3.Gestor_Salud.features.Recetas;

import com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.DTOs.RecetaNueva;
import com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.DTOs.RecetaRespuesta;

import java.util.List;

public interface IServicioReceta {
    RecetaRespuesta crear (RecetaNueva receta);
    RecetaRespuesta buscarPorId(Long id);
    List<RecetaRespuesta> buscarTodos();
    List<RecetaRespuesta> buscarPorPaciente (Long id_paciente);
}
