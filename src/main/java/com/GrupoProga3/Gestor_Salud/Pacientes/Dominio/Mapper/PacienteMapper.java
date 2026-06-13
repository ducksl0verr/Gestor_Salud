package com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.Mapper;
import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.EntidadDiagnostico;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteNuevo;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.MAPPER.PrescripcionTratamientoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    @Mapping(source = "obraSocial.id", target = "id_obraSocial")
    @Mapping(source = "obraSocial.nombre", target = "nombreObraSocial")
    @Mapping(source = "obraSocial.cobertura", target = "coberturaObraSocial")
    PacienteRespuesta toDTO (EntidadPaciente entidadPaciente);

    /// Este método lo puse acá para que cuando el usuario cree un nuevo paciente
    /// una vez que ponga su domicilio, este se mapee automaticamente -Dante
    EntidadDomicilio toEntity (DomicilioNuevo domicilio);

    EntidadContacto toEntity (ContactoNuevo contacto);

    @Mapping(target = "obraSocial", ignore = true)
    EntidadPaciente toEntity (PacienteNuevo pacienteNuevo);
}
