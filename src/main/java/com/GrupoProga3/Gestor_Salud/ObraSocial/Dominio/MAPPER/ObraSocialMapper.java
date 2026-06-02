package com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialNueva;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.ObraSocialRespuesta;
import com.GrupoProga3.Gestor_Salud.ObraSocial.EntidadObraSocial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObraSocialMapper {
    EntidadObraSocial toEntity (ObraSocialNueva obrasSocial);
    ObraSocialRespuesta toDTO (EntidadObraSocial entidadSocial);
}
