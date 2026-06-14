package com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.Mappers;

import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.EntidadDomicilio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomicilioMapper {
    EntidadDomicilio toEntity (DomicilioNuevo domicilioNuevo);
    DomicilioRespuesta toDto (EntidadDomicilio entidadDomicilio);
}
