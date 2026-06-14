package com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.Mappers;


import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    //UsuarioDTO ToDto(EntidadUsuarios entidadUsuarios);
    EntidadUsuarios ToEntity(UsuarioDTO usuarioDTO);
    //ProfesionalDTO ProfToDTO (EntidadUsuarios entidadUsuarios);
    EntidadUsuarios ProfToEntity (ProfesionalDTO profesionalDTO);
    EntidadContacto toEntity (ContactoNuevo contacto);
    @Mapping(source = "id", target = "idUsuarioProfesional")
    ProfesionalRespuestaDTO toRespuestaProfesionalDTO (EntidadUsuarios entidadUsuarios);
    @Mapping(source = "id", target = "idUsuario")
    UsuarioRespuestaDTO toRespuestaUsuarioDTO(EntidadUsuarios entidadUsuarios);

}
