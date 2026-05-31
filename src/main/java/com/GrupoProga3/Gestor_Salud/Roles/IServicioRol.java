package com.GrupoProga3.Gestor_Salud.Roles;

import com.GrupoProga3.Gestor_Salud.Roles.Dominio.DTOs.RolActualizar;
import com.GrupoProga3.Gestor_Salud.Roles.Dominio.DTOs.RolRespuesta;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface IServicioRol {
    RolRespuesta guardar (RolActualizar rolActualizar);
    RolRespuesta actualizar (Long id, RolActualizar rolActualizar);
    RolRespuesta buscarPorId(Long id);
    List<RolRespuesta> buscarTodos();
}
