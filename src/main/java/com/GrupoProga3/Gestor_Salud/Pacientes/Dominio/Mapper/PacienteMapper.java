package com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.Mapper;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteNuevo;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    @Mapping(source = "obraSocial.id",
    target = "id_obraSocial")
    PacienteRespuesta toDTO (EntidadPaciente entidadPaciente);

    /// Este método lo puse acá para que cuando el usuario cree un nuevo paciente
    /// una vez que ponga su domicilio, este se mapee automaticamente -Dante
    EntidadDomicilio toEntity (DomicilioNuevo domicilio);

    @Mapping(target = "obraSocial", ignore = true)
    EntidadPaciente toEntity (PacienteNuevo pacienteNuevo);
}
