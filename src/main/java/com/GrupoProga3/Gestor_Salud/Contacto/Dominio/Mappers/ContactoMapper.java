package com.GrupoProga3.Gestor_Salud.Contacto.Dominio.Mappers;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoDTO;
import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;

public interface ContactoMapper {
    EntidadContacto toEntity(ContactoDTO contactoDTODTO);
    ContactoDTO toDto (EntidadContacto entidadContacto);
}
