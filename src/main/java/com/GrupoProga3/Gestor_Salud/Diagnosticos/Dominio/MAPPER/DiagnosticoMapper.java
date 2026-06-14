package com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs.DiagnosticoNuevo;
import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;
import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.EntidadDiagnostico;
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
