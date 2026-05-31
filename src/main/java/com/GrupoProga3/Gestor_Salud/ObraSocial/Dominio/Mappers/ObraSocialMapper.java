package com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.Mappers;

import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialDTO;
import com.GrupoProga3.Gestor_Salud.ObraSocial.EntidadObraSocial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObraSocialMapper {

    EntidadObraSocial toEntity(ObraSocialDTO dto);

    ObraSocialDTO toDto(EntidadObraSocial entidad);
}