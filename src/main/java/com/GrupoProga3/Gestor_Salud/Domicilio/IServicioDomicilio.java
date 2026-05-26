package com.GrupoProga3.Gestor_Salud.Domicilio;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.Mappers.DTO.DomicilioDTO;

import java.util.List;

public interface IServicioDomicilio {
    DomicilioDTO guardar (DomicilioDTO domicilioDTO);
    void borrar (Long id);
    DomicilioDTO buscarPorId(Long id);
    DomicilioDTO actualizar (Long id, DomicilioDTO domicilioDTO);
    List<DomicilioDTO> buscarTodos();
}
