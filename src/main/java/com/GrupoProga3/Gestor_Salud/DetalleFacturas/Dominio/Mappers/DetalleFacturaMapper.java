package com.GrupoProga3.Gestor_Salud.DetalleFacturas.Dominio.Mappers;

import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Dominio.DTO.DetalleFacturaDTO;
import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Model.EntidadDetalleFacturas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetalleFacturaMapper {
    DetalleFacturaDTO toDTO (EntidadDetalleFacturas entidadDetalleFacturas);
    EntidadDetalleFacturas toEntity (DetalleFacturaDTO detalleFacturaDTO);
}
