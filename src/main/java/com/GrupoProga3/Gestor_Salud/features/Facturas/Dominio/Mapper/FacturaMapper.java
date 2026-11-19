package com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.Mapper;

import com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.DTO.FacturaRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Model.EntidadFacturas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    @Mapping(target = "idPaciente", source = "paciente.id")
    FacturaRespuesta toRespuestaDTO(EntidadFacturas factura);

}
