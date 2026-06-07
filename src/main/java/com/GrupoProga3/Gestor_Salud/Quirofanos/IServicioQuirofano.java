package com.GrupoProga3.Gestor_Salud.Quirofanos;

import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoActualizar;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoNuevo;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoRespuesta;

import java.util.List;

public interface IServicioQuirofano {
    QuirofanoRespuesta crear (QuirofanoNuevo quirofanoNuevo);
    QuirofanoRespuesta actualizar (Long id, QuirofanoActualizar quirofanoActualizar);
    QuirofanoRespuesta buscarPorId(Long id);
    List<QuirofanoRespuesta> buscarTodos();
}
