package com.GrupoProga3.Gestor_Salud.Contacto.Servicio;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoDTO;

import java.util.List;

public interface IServicioContacto {
    ContactoDTO guardar(ContactoDTO contactoDTO);
    void borrar(Long id);
    ContactoDTO buscarPorId(Long Id);
    ContactoDTO  actualizar(Long id, ContactoDTO contactoDTO);
    List<ContactoDTO> buscarTodos();

}
