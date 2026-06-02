package com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.MAPPER;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.Dominio.DTOs.PrescripcionTratamientoNueva;
import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.Dominio.PrescripcionTratamientoRespuesta;
import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.EntidadPrescripcionTratamiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrescripcionTratamientoMapper {
    @Mapping(target = "pacientes", ignore = true)
    @Mapping(target = "tratamientos", ignore = true)
    EntidadPrescripcionTratamiento toEntity (PrescripcionTratamientoNueva  prescripcionTratamiento);

    @Mapping(source="tratamientos.id",
    target = "id_tratamiento")
    @Mapping(source = "tratamientos.nombre",
    target = "nombre_tratamiento")
    PrescripcionTratamientoRespuesta toDTO(EntidadPrescripcionTratamiento tratamiento);
}
