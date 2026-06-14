package com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.DetallesRecetas.Dominio.DTOs.DetalleRecetaNuevo;
import com.GrupoProga3.Gestor_Salud.features.DetallesRecetas.Dominio.DTOs.DetalleRecetaRespuesta;
import com.GrupoProga3.Gestor_Salud.features.DetallesRecetas.Dominio.EntidadDetalleReceta;
import com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.DTOs.RecetaNueva;
import com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.DTOs.RecetaRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.EntidadReceta;
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


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicamento", ignore = true)
    @Mapping(target = "receta", ignore = true)
    EntidadDetalleReceta toEntity (DetalleRecetaNuevo detalleRecetaNuevo);


    @Mapping(source = "medicamento.nombre",
            target = "nombreMedicamento")
    @Mapping(source = "medicamento.id",
            target = "idMedicamento")
    DetalleRecetaRespuesta toDTO (EntidadDetalleReceta entidadDetalleReceta);

}
