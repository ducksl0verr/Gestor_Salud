package com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.DTOs.CirugiaRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.DTOs.CirugiaNueva;
import com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.EntidadCirugia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CirugiaMapper {
    @Mapping(source = "paciente.id",
        target = "idPaciente")
    @Mapping(source = "paciente.nombre",
            target = "nombrePaciente")

    @Mapping(source = "cirujano.id",
            target = "idCirujano")
    @Mapping(source = "cirujano.nombre",
            target = "nombreCirujano")

    @Mapping(source = "quirofano.id",
            target = "idQuirofano")
    @Mapping(source = "quirofano.nombre",
            target = "nombreQuirofano")
    CirugiaRespuesta toDTO (EntidadCirugia  entidadCirugia);

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target ="quirofano", ignore = true)
    @Mapping(target = "cirujano", ignore = true)
    @Mapping(target = "estado", ignore = true)
    EntidadCirugia toEntity (CirugiaNueva cirugiaNueva);
}
