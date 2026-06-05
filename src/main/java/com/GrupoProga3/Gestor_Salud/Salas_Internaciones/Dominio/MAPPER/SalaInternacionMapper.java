package com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionActualizar;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionNueva;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionRespuesta;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.EntidadSalaInternacion;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface SalaInternacionMapper {
    EntidadSalaInternacion toEntity (SalaInternacionNueva  salaInternacionNueva);
    SalaInternacionRespuesta toDTO (EntidadSalaInternacion entidadSalaInternacion);
    PacienteRespuesta toDTO(EntidadPaciente entidadPaciente);

}
