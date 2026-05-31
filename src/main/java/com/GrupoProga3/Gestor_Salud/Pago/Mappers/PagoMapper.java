package com.GrupoProga3.Gestor_Salud.Pago.Mappers;

import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoDTO;
import com.GrupoProga3.Gestor_Salud.Pago.EntidadPago;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface PagoMapper {
    EntidadPago toEntity(PagoDTO dto);
    PagoDTO toDto(EntidadPago entidadPago);
}