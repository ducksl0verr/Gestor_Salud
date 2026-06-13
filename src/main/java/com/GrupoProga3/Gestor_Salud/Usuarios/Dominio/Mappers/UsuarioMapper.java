package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.Mappers;


import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO ToDto(EntidadUsuarios entidadUsuarios);
    EntidadUsuarios ToEntity(UsuarioDTO usuarioDTO);
    ProfesionalDTO ProfToDTO (EntidadUsuarios entidadUsuarios);
    EntidadUsuarios ProfToEntity (ProfesionalDTO profesionalDTO);

    ProfesionalRespuestaDTO toRespuestaProfesionalDTO (EntidadUsuarios entidadUsuarios);
    UsuarioRespuestaDTO toRespuestaUsuarioDTO(EntidadUsuarios entidadUsuarios);
}
