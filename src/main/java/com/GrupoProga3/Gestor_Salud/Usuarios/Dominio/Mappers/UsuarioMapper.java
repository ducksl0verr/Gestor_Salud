package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.Mappers;


import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.EntidadUsuarios;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO ToDto(EntidadUsuarios entidadUsuarios);
    EntidadUsuarios ToEntity(UsuarioDTO usuarioDTO);
}
