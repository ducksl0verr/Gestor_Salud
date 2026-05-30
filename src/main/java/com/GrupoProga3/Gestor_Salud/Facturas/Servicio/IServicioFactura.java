package com.GrupoProga3.Gestor_Salud.Facturas.Servicio;

import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaDTO;

import java.util.List;

public interface IServicioFactura {
    FacturaDTO guardar (FacturaDTO facturaDTO);
    void borrar (Long id);
    FacturaDTO actualizar(Long id, FacturaDTO facturaDTO);
    FacturaDTO buscarPorId(Long id);
    List<FacturaDTO> buscarTodos();
}
