package com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.DTOs.QuirofanoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.DTOs.QuirofanoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.EntidadQuirofano;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuirofanoMapper {
    QuirofanoRespuesta toDTO (EntidadQuirofano entidadQuirofano);
    @Mapping(target="disponible", ignore = true)
    EntidadQuirofano toEntity(QuirofanoNuevo quirofanoNuevo);
}
