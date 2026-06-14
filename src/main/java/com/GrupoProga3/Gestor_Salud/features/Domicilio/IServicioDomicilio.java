package com.GrupoProga3.Gestor_Salud.features.Domicilio;

import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioRespuesta;

import java.util.List;

public interface IServicioDomicilio {
    DomicilioRespuesta guardar (DomicilioNuevo domicilioNuevo);
    void borrar (Long id);
    DomicilioRespuesta buscarPorId(Long id);
    DomicilioRespuesta actualizar (Long id, DomicilioNuevo domicilioNuevo);
    List<DomicilioRespuesta> buscarTodos();
}
