package com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs.DiagnosticoNuevo;
import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;
import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.EntidadDiagnostico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiagnosticoMapper {
    EntidadDiagnostico toEntity (DiagnosticoNuevo diagnostico);
    DiagnosticoRespuesta toDTO (EntidadDiagnostico entidadDiagnostico);
}
