package com.GrupoProga3.Gestor_Salud.features.Roles.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.Roles.Dominio.DTOs.RolActualizar;
import com.GrupoProga3.Gestor_Salud.features.Roles.Dominio.DTOs.RolRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Roles.Dominio.EntidadRol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {
    EntidadRol toEntity (RolActualizar rolActualizar);
    RolRespuesta toDTO (EntidadRol entidadRol);
}
