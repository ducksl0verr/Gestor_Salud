package com.GrupoProga3.Gestor_Salud.features.Diagnosticos;

import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs.DiagnosticoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;

import java.util.List;

public interface IServicioDiagnostico {
    DiagnosticoRespuesta crear (DiagnosticoNuevo  diagnosticoNuevo);
    List<DiagnosticoRespuesta> buscarTodos();
    DiagnosticoRespuesta buscarPorId(Long id);
    DiagnosticoRespuesta actualizar (Long id, DiagnosticoNuevo diagnosticoNuevo);
    void borrar (Long id);
}
