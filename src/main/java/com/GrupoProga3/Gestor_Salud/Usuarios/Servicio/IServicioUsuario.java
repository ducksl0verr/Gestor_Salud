package com.GrupoProga3.Gestor_Salud.Usuarios.Servicio;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import java.util.List;

public interface IServicioUsuario {
    UsuarioDTO guardar (UsuarioDTO usuarioDTO);
    ProfesionalDTO guardarProfesional (ProfesionalDTO profesionalDTO);
    void borrar (Long id);
    UsuarioDTO buscarPorId(Long id);
    ProfesionalDTO buscarPorIdProfesional(Long id);
    UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO);
    ProfesionalDTO actualizarProfesional(Long id, ProfesionalDTO profesionalDTO);
    List<UsuarioDTO> buscarTodos();
    List<ProfesionalDTO> buscarTodosProfesionales();
}