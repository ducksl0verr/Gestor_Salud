package com.GrupoProga3.Gestor_Salud.Facturas.Dominio.Mapper;

import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaNueva;
import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaRespuesta;
import com.GrupoProga3.Gestor_Salud.Facturas.Model.EntidadFacturas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    FacturaRespuesta toRespuestaDTO(EntidadFacturas factura);

}
