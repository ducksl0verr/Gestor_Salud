package com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.DTOs.PrescripcionTratamientoNueva;
import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.DTOs.PrescripcionTratamientoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.EntidadPrescripcionTratamiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrescripcionTratamientoMapper {
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "tratamientos", ignore = true)
    @Mapping(target = "profesional", ignore = true)
    @Mapping(target = "activo", ignore = true)
    EntidadPrescripcionTratamiento toEntity (PrescripcionTratamientoNueva  prescripcionTratamiento);

    @Mapping(source="tratamientos.id",
    target = "id_tratamiento")
    @Mapping(source = "tratamientos.nombre",
    target = "nombre_tratamiento")
    PrescripcionTratamientoRespuesta toDTO(EntidadPrescripcionTratamiento tratamiento);
}
