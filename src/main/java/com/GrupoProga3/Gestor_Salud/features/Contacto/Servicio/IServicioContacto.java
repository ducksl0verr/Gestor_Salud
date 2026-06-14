package com.GrupoProga3.Gestor_Salud.features.Contacto.Servicio;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;

import java.util.List;

public interface IServicioContacto {

    ContactoRespuesta guardar(ContactoNuevo contactoNuevo);

    ContactoRespuesta actualizar(Long id,
                                 ContactoNuevo contactoNuevo);

    void borrar(Long id);

    ContactoRespuesta buscarPorId(Long id);

    List<ContactoRespuesta> buscarTodos();

    //List<ContactoRespuesta> buscarContacto(String nombre, String apellido);
}