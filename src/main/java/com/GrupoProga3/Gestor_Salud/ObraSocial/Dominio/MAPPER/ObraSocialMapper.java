package com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialNueva;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialRespuesta;
import com.GrupoProga3.Gestor_Salud.ObraSocial.EntidadObraSocial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ObraSocialMapper {

    EntidadObraSocial toEntity(ObraSocialNueva dto);

    ObraSocialRespuesta toDTO(EntidadObraSocial entity);

    EntidadDomicilio toEntity(DomicilioNuevo dto);

    DomicilioNuevo toDTO(EntidadDomicilio entity);
}
