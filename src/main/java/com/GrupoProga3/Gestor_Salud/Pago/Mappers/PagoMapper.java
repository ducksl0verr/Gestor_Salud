package com.GrupoProga3.Gestor_Salud.Pago.Mappers;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoNuevo;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoRespuesta;
import com.GrupoProga3.Gestor_Salud.Pago.EntidadPago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PagoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pacientes", ignore = true)
    EntidadPago toEntity(PagoNuevo dto);

    PagoRespuesta toDTO(EntidadPago entidadPago);

    PacienteRespuesta toPacienteDTO(EntidadPaciente paciente);
}
