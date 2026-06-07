package com.GrupoProga3.Gestor_Salud.Facturas.Servicio;

import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaNueva;
import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaRespuesta;

import java.util.List;

public interface IServicioFactura {
    FacturaRespuesta buscarPorId(Long id);
    List<FacturaRespuesta> buscarTodos();
    FacturaRespuesta crearFactura(FacturaNueva facturaNueva);
}
