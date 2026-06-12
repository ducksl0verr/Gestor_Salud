package com.GrupoProga3.Gestor_Salud.Pago.Mappers;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialNueva;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialRespuesta;
import com.GrupoProga3.Gestor_Salud.ObraSocial.EntidadObraSocial;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoDTO;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoNuevo;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoRespuesta;
import com.GrupoProga3.Gestor_Salud.Pago.EntidadPago;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PagoMapper {

    EntidadPago toEntity(PagoNuevo dto);

    PagoRespuesta toDTO(EntidadPago entidadPago);

    PacienteRespuesta toPacienteDTO(EntidadPaciente paciente);
}
