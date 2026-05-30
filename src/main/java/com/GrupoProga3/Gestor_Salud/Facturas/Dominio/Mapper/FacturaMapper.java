package com.GrupoProga3.Gestor_Salud.Facturas.Dominio.Mapper;

import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaDTO;
import com.GrupoProga3.Gestor_Salud.Facturas.Model.EntidadFacturas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacturaMapper {
    FacturaDTO toDTO (EntidadFacturas entidadFacturas);
    EntidadFacturas toEntity (FacturaDTO facturaDTO);
}
