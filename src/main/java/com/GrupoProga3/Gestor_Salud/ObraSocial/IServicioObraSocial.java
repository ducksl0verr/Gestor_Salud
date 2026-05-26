package com.GrupoProga3.Gestor_Salud.ObraSocial;

import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialDTO;

import java.util.List;

public interface IServicioObraSocial {
    ObraSocialDTO guardar (ObraSocialDTO obrasocialDTO); // Crear
    void borrar (Long id);// delete
    ObraSocialDTO buscarPorId(Long id); //findbyid READ
    ObraSocialDTO actualizar (Long id, ObraSocialDTO obrasocialDTO);//update
    List<ObraSocialDTO> buscarTodos();//findAll
}
