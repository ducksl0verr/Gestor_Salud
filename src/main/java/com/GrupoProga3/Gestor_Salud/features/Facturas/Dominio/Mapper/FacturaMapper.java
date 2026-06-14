package com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.Mapper;

import com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.DTO.FacturaRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Model.EntidadFacturas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    FacturaRespuesta toRespuestaDTO(EntidadFacturas factura);

}
