package com.GrupoProga3.Gestor_Salud.Usuarios.Servicio;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;

import java.util.List;

public interface IServicioUsuario {
    UsuarioRespuestaDTO guardar (UsuarioDTO usuarioDTO);
    ProfesionalRespuestaDTO guardarProfesional (ProfesionalDTO profesionalDTO);
    void borrar (Long id);
    UsuarioRespuestaDTO buscarPorId(Long id);
    ProfesionalRespuestaDTO buscarPorIdProfesional(Long id);
    UsuarioRespuestaDTO actualizar(Long id, UsuarioDTO usuarioDTO);
    ProfesionalRespuestaDTO actualizarProfesional(Long id, ProfesionalDTO profesionalDTO);
    List<UsuarioRespuestaDTO> buscarTodos();
    List<ProfesionalRespuestaDTO> buscarTodosProfesionales();
}