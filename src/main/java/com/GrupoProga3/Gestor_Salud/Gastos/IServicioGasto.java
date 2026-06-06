package com.GrupoProga3.Gestor_Salud.Gastos;

import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs.GastoActualizar;
import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs.GastoNuevo;
import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs.GastoRespuesta;

import java.util.List;

public interface IServicioGasto {
    GastoRespuesta crear (GastoNuevo gastoNuevo);
    GastoRespuesta buscarPorId(Long id);
    List<GastoRespuesta> buscarTodos();
    List<GastoRespuesta> buscarPorProveedor(String nombre);
    GastoRespuesta actualizar (Long id, GastoActualizar gastoActualizar);
    void borrar (Long id);
}
