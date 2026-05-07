package com.GrupoProga3.Gestor_Salud.InterfazMapper;


import com.GrupoProga3.Gestor_Salud.Usuarios.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.Usuarios.UsuarioDTO;

@org.mapstruct.Mapper(componentModel = "Spring")
public interface Mapper{

    UsuarioDTO ToDto(EntidadUsuarios usuario);
    EntidadUsuarios ToEntity(UsuarioDTO dto);
}
