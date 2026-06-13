package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.Mappers;


import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO ToDto(EntidadUsuarios entidadUsuarios);
    EntidadUsuarios ToEntity(UsuarioDTO usuarioDTO);
    ProfesionalDTO ProfToDTO (EntidadUsuarios entidadUsuarios);
    EntidadUsuarios ProfToEntity (ProfesionalDTO profesionalDTO);
    EntidadContacto toEntity (ContactoNuevo contacto);
    @Mapping(source = "id", target = "idUsuarioProfesional")
    ProfesionalRespuestaDTO toRespuestaProfesionalDTO (EntidadUsuarios entidadUsuarios);
    @Mapping(source = "id", target = "idUsuario")
    UsuarioRespuestaDTO toRespuestaUsuarioDTO(EntidadUsuarios entidadUsuarios);

}
