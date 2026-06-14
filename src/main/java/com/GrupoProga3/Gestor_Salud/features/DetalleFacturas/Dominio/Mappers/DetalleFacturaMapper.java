package com.GrupoProga3.Gestor_Salud.features.DetalleFacturas.Dominio.Mappers;

import com.GrupoProga3.Gestor_Salud.features.DetalleFacturas.Dominio.DTO.DetalleFacturaDTO;
import com.GrupoProga3.Gestor_Salud.features.DetalleFacturas.Model.EntidadDetalleFacturas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetalleFacturaMapper {
    DetalleFacturaDTO toDTO (EntidadDetalleFacturas entidadDetalleFacturas);
    EntidadDetalleFacturas toEntity (DetalleFacturaDTO detalleFacturaDTO);
}
