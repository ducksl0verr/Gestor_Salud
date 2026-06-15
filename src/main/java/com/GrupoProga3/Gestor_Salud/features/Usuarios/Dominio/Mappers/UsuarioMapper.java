package com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.Mappers;


import com.GrupoProga3.Gestor_Salud.auth.DTOs.CuentaNueva;
import com.GrupoProga3.Gestor_Salud.auth.permisos.EntidadRole;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    //UsuarioDTO ToDto(EntidadUsuarios entidadUsuarios);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    EntidadUsuarios ToEntity(CuentaNueva dto);
    //ProfesionalDTO ProfToDTO (EntidadUsuarios entidadUsuarios);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    EntidadUsuarios ProfToEntity (CuentaNueva dto);
    EntidadContacto toEntity (ContactoNuevo contacto);
    @Mapping(source = "id", target = "idUsuarioProfesional")
    @Mapping(target = "role", source = "role.role")
    ProfesionalRespuestaDTO toRespuestaProfesionalDTO (EntidadUsuarios entidadUsuarios);
    @Mapping(source = "id", target = "idUsuario")
    @Mapping(target = "role", source = "role.role")
    UsuarioRespuestaDTO toRespuestaUsuarioDTO(EntidadUsuarios entidadUsuarios);

}
