package com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs.DiagnosticoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.EntidadDiagnostico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiagnosticoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "historiaClinica", ignore = true)
    EntidadDiagnostico toEntity (DiagnosticoNuevo diagnostico);

    @Mapping(target = "idHistoriaClinica",
    source = "historiaClinica.id")
    DiagnosticoRespuesta toDTO (EntidadDiagnostico entidadDiagnostico);


}
