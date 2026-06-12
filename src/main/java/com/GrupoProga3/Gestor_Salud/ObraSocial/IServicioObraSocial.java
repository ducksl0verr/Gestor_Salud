package com.GrupoProga3.Gestor_Salud.ObraSocial;

import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialDTO;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialNueva;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialRespuesta;

import java.util.List;

public interface IServicioObraSocial {
    ObraSocialRespuesta guardar (ObraSocialNueva obrasocialDTO); // Crear
    void borrar (Long id);// delete
    ObraSocialRespuesta actualizar (Long id, ObraSocialDTO obrasocialDTO);//update
    List<ObraSocialRespuesta> buscarTodos();//findAll
}
