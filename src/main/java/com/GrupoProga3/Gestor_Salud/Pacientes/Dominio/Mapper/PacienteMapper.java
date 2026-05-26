package com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.Mapper;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteDTO;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteDTO toDTO (EntidadPaciente entidadPaciente);
    EntidadPaciente toEntity (PacienteDTO pacienteDTO);
}
