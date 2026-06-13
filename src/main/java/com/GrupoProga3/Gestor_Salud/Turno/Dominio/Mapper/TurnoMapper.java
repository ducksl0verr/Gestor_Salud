package com.GrupoProga3.Gestor_Salud.Turno.Dominio.Mapper;

import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoFacturable;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoRespuesta;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface TurnoMapper {

    @Mapping(target="paciente", ignore = true)
    @Mapping(target="tratamiento", ignore = true)
    @Mapping(target="consultorio", ignore = true)
    @Mapping(target="profesional", ignore = true)
    @Mapping(target = "estadoTurno", ignore = true)
    @Mapping(target = "estadoFacturacionDeTurno", ignore = true)
    EntidadTurno toEntity (TurnoNuevo turnoNuevo);

    @Mapping(source="paciente.id", target="id_paciente")
    @Mapping(source="tratamiento.id", target="id_tratamiento")
    @Mapping(source="consultorio.id", target="id_consultorio")
    @Mapping(source="profesional.id", target="id_profesional")
    TurnoRespuesta toRespuestaDto (EntidadTurno entidadTurno);

    @Mapping(target = "tratamiento", source = "tratamiento.nombre")
    TurnoFacturable toFacturableDTO(EntidadTurno turno);



}
