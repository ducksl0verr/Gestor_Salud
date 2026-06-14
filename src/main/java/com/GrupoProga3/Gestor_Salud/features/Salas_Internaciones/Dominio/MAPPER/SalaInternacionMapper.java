package com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs.SalaInternacionNueva;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs.SalaInternacionRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.EntidadSalaInternacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface SalaInternacionMapper {
    EntidadSalaInternacion toEntity (SalaInternacionNueva  salaInternacionNueva);
    SalaInternacionRespuesta toDTO (EntidadSalaInternacion entidadSalaInternacion);

    @Mapping(source = "obraSocial.id", target = "id_obraSocial")
    @Mapping(source = "obraSocial.nombre", target = "nombreObraSocial")
    @Mapping(source = "obraSocial.cobertura", target = "coberturaObraSocial")
    PacienteRespuesta toDTO(EntidadPaciente entidadPaciente);
    DomicilioRespuesta toDTO (DomicilioNuevo domicilio);
    ContactoRespuesta toDTO (ContactoNuevo contacto);

}
