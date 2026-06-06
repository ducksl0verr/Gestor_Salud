package com.GrupoProga3.Gestor_Salud.Gastos.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs.GastoNuevo;
import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs.GastoRespuesta;
import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.EntidadGasto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface GastoMapper {
    @Mapping(source = "proveedor.nombre",
    target = "nombreProveedor")
    GastoRespuesta toDTO (EntidadGasto entidadGasto);
    @Mapping(target = "proveedor", ignore = true)
    EntidadGasto toEntity (GastoNuevo dto);
}
