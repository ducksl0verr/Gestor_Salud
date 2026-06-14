package com.GrupoProga3.Gestor_Salud.features.Cirugias;

import com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.DTOs.CirugiaRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.DTOs.CirugiaActualizar;
import com.GrupoProga3.Gestor_Salud.features.Cirugias.Dominio.DTOs.CirugiaNueva;

import java.util.List;

public interface IServicioCirugia {
    CirugiaRespuesta crear (CirugiaNueva cirugiaNueva);
    CirugiaRespuesta buscarPorID (Long id);
    List<CirugiaRespuesta> buscarTodos();
    CirugiaRespuesta actualizar (Long id, CirugiaActualizar cirugiaActualizar);
    void borrar (Long id);
}
