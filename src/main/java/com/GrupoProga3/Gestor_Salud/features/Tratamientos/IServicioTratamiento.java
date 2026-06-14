package com.GrupoProga3.Gestor_Salud.features.Tratamientos;


import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.DTOs.TratamientoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.DTOs.TratamientoRespuesta;

import java.util.List;

public interface IServicioTratamiento {
    TratamientoRespuesta crear (TratamientoNuevo tratamientoNuevo);
    List<TratamientoRespuesta> buscarTodos();
    TratamientoRespuesta buscarPorId(Long id);
    TratamientoRespuesta actualizar (Long id, TratamientoNuevo dto);
    /// No creo que sea necesario actualizar un tratamiento
    void borrar (Long id);
}
