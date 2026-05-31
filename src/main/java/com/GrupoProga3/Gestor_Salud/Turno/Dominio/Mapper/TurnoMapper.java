package com.GrupoProga3.Gestor_Salud.Turno.Dominio.Mapper;

import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoRespuesta;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface TurnoMapper {
 //   EntidadTurno toEntity (TurnoRespuesta turnoRespuesta);

    @Mapping(target="id_paciente", ignore = true)
    @Mapping(target="id_tratamiento", ignore = true)
    @Mapping(target="id_sala", ignore = true)
    @Mapping(target="id_profesional", ignore = true)
    EntidadTurno toEntity (TurnoNuevo turnoNuevo);
    @Mapping(source="id_paciente.id", target="id_paciente")
    @Mapping(source="id_tratamiento.id", target="id_tratamiento")
    @Mapping(source="id_sala.id", target="id_sala")
    @Mapping(source="id_profesional.id", target="id_profesional")
    TurnoRespuesta toDto (EntidadTurno entidadTurno);
}
