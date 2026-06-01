package com.GrupoProga3.Gestor_Salud.Especialidades.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Especialidades.Dominio.DTOs.EspecialidadNueva;
import com.GrupoProga3.Gestor_Salud.Especialidades.Dominio.DTOs.EspecialidadRespuesta;
import com.GrupoProga3.Gestor_Salud.Especialidades.Dominio.EntidadEspecialidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper {
    EntidadEspecialidad toEntity (EspecialidadNueva especialidad);
    EspecialidadRespuesta toDTO (EntidadEspecialidad especialidad);
}
