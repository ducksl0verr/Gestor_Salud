package com.GrupoProga3.Gestor_Salud.Usuarios;


import com.GrupoProga3.Gestor_Salud.Domicilio.EntidadDomicilio;

@org.mapstruct.Mapper(componentModel = "Spring")
public interface UsuarioMapper {

    UsuarioDTO ToDto(EntidadUsuarios usuario);
    EntidadUsuarios ToEntity(UsuarioDTO dto);

}
