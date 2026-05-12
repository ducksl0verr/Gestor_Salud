package com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.Mappers;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioDTO;
import com.GrupoProga3.Gestor_Salud.Domicilio.EntidadDomicilio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomicilioMapper {
    EntidadDomicilio toEntity (DomicilioDTO domicilioDTO);
    DomicilioDTO toDto (EntidadDomicilio entidadDomicilio);
}
