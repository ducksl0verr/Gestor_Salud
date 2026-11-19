package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.Mapper;

import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.EntidadConsultorio;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoFacturable;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.EntidadTurno;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface TurnoMapper {

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "tratamiento", ignore = true)
    @Mapping(target = "consultorio", ignore = true)
    @Mapping(target = "profesional", ignore = true)
    @Mapping(target = "estadoTurno", ignore = true)
    @Mapping(target = "estadoFacturacionDeTurno", ignore = true)
    EntidadTurno toEntity(TurnoNuevo turnoNuevo);

    default TurnoRespuesta toRespuestaDto(EntidadTurno turno) {
        if (turno == null) {
            return null;
        }

        return new TurnoRespuesta(
                turno.getId(),
                turno.getFechaHora(),
                turno.getEstadoFacturacionDeTurno(),
                turno.getEstadoTurno(),
                nombreCompleto(turno.getPaciente()),
                nombreTratamiento(turno.getTratamiento()),
                nombreConsultorio(turno.getConsultorio()),
                nombreCompleto(turno.getProfesional())
        );
    }

    default TurnoFacturable toFacturableDTO(EntidadTurno turno) {
        if (turno == null) {
            return null;
        }

        BigDecimal monto = turno.getTratamiento() == null
                || turno.getTratamiento().getPrecio() == null
                ? BigDecimal.ZERO
                : turno.getTratamiento().getPrecio();

        return new TurnoFacturable(
                turno.getId(),
                turno.getFechaHora(),
                nombreTratamiento(turno.getTratamiento()),
                monto
        );
    }

    private String nombreCompleto(EntidadPaciente paciente) {
        if (paciente == null) return "";
        return ((nullSafe(paciente.getNombre()) + " " + nullSafe(paciente.getApellido())).trim());
    }

    private String nombreCompleto(EntidadUsuarios profesional) {
        if (profesional == null) return "";
        return ((nullSafe(profesional.getNombre()) + " " + nullSafe(profesional.getApellido())).trim());
    }

    private String nombreConsultorio(EntidadConsultorio consultorio) {
        return consultorio == null ? "" : nullSafe(consultorio.getNombre());
    }

    private String nombreTratamiento(EntidadTratamiento tratamiento) {
        return tratamiento == null ? "Consulta sin tratamiento" : nullSafe(tratamiento.getNombre());
    }

    private String nullSafe(String valor) {
        return valor == null ? "" : valor.trim();
    }
}
