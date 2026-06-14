package com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.Mappers;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactoMapper {

    EntidadContacto toEntity(ContactoNuevo dto);
    ContactoRespuesta toDTO(EntidadContacto entity);

}