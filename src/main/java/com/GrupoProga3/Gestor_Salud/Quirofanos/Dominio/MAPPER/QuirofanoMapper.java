package com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoNuevo;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoRespuesta;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.EntidadQuirofano;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuirofanoMapper {
    QuirofanoRespuesta toDTO (EntidadQuirofano entidadQuirofano);
    EntidadQuirofano toEntity(QuirofanoNuevo quirofanoNuevo);
}
