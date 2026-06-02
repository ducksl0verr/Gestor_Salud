package com.GrupoProga3.Gestor_Salud.Tratamientos;


import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.DTOs.TratamientoNuevo;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.DTOs.TratamientoRespuesta;

import java.util.List;

public interface IServicioTratamiento {
    TratamientoRespuesta crear (TratamientoNuevo tratamientoNuevo);
    List<TratamientoRespuesta> buscarTodos();
    TratamientoRespuesta buscarPorId(Long id);
    //TratamientoRespuesta actualizar (Long id, HistoriaClinicaActualizar actualizacion);
    /// No creo que sea necesario actualizar un tratamiento
    void borrar (Long id);
}
