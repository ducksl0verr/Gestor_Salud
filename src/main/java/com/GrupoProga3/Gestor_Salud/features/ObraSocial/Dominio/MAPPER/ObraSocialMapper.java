package com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.DTO.ObraSocialNueva;
import com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.DTO.ObraSocialRespuesta;
import com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.EntidadObraSocial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ObraSocialMapper {

    EntidadObraSocial toEntity(ObraSocialNueva dto);

    ObraSocialRespuesta toDTO(EntidadObraSocial entity);

    EntidadDomicilio toEntity(DomicilioNuevo dto);

    DomicilioNuevo toDTO(EntidadDomicilio entity);
}
