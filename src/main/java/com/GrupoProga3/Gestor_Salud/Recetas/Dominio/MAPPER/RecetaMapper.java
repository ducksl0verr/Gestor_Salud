package com.GrupoProga3.Gestor_Salud.Recetas.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.DTOs.RecetaNueva;
import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.DTOs.RecetaRespuesta;
import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.EntidadReceta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecetaMapper {

    @Mapping(target = "fecha", ignore = true)
    EntidadReceta toEntity (RecetaNueva receta);

    @Mapping(source = "paciente.id",
    target = "id_paciente")
    @Mapping(source = "profesional.id",
    target = "id_profesional")
    RecetaRespuesta toDTO (EntidadReceta entidadReceta);

}
