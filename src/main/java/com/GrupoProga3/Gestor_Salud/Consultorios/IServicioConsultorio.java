package com.GrupoProga3.Gestor_Salud.Consultorios;

import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs.ConsultorioActualizar;
import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs.ConsultorioNuevo;
import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs.ConsultorioRespuesta;

import java.util.List;

public interface IServicioConsultorio {
    ConsultorioRespuesta crear (ConsultorioNuevo  consultorioNuevo);
    ConsultorioRespuesta buscarPorId(Long id);
    List<ConsultorioRespuesta> buscarTodos();
    ConsultorioRespuesta actualizar (Long id, ConsultorioActualizar consultorioActualizar);
    void borrar (Long id);
}
