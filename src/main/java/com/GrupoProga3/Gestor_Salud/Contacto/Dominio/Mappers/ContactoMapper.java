package com.GrupoProga3.Gestor_Salud.Contacto.Dominio.Mappers;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoDTO;
import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoRespuesta;
import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactoMapper {

    EntidadContacto toEntity(ContactoNuevo dto);

    ContactoRespuesta toDTO(EntidadContacto entity);

}