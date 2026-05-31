package com.GrupoProga3.Gestor_Salud.HistoriaClinica;

import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaActualizar;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaNueva;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaRespuesta;

import java.util.List;

public interface IServicioHistoriaClinica {
    HistoriaClinicaRespuesta crear (HistoriaClinicaNueva historiaClinicaNueva);
    List<HistoriaClinicaRespuesta> buscarTodos();
    HistoriaClinicaRespuesta buscarPorId(Long id);
    HistoriaClinicaRespuesta actualizar (Long id, HistoriaClinicaActualizar actualizacion);
    void borrar (Long id);
}
