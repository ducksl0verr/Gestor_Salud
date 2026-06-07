package com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.DTOs.DetalleRecetaNuevo;
import com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.DTOs.DetalleRecetaRespuesta;
import com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.EntidadDetalleReceta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleRecetaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicamento", ignore = true)
    @Mapping(target = "receta", ignore = true)
    EntidadDetalleReceta toEntity (DetalleRecetaNuevo detalleRecetaNuevo);

    @Mapping(source = "medicamento.nombre",
    target = "nombreMedicamento")
    DetalleRecetaRespuesta  toDTO (EntidadDetalleReceta entidadDetalleReceta);

}
