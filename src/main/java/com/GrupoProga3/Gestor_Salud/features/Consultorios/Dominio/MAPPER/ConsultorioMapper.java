package com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.EntidadConsultorio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultorioMapper {
    EntidadConsultorio toEntity (ConsultorioNuevo consultorioNuevo);
    ConsultorioRespuesta toDTO (EntidadConsultorio entidadConsultorio);
}
