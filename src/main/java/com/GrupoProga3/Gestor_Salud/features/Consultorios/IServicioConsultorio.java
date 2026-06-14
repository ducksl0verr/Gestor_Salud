package com.GrupoProga3.Gestor_Salud.features.Consultorios;

import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioActualizar;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioRespuesta;

import java.util.List;

public interface IServicioConsultorio {
    ConsultorioRespuesta crear (ConsultorioNuevo  consultorioNuevo);
    ConsultorioRespuesta buscarPorId(Long id);
    List<ConsultorioRespuesta> buscarTodos();
    ConsultorioRespuesta actualizar (Long id, ConsultorioActualizar consultorioActualizar);
    void borrar (Long id);
}
