package com.GrupoProga3.Gestor_Salud.features.Usuarios.Servicio;

import com.GrupoProga3.Gestor_Salud.auth.DTOs.CuentaNueva;
import com.GrupoProga3.Gestor_Salud.features.Notificaciones.MensajeDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;

import java.util.List;

public interface IServicioUsuario {
    UsuarioRespuestaDTO guardar (CuentaNueva nueva);
    ProfesionalRespuestaDTO guardarProfesional (CuentaNueva nueva);
    void borrar (Long id);
    UsuarioRespuestaDTO buscarPorId(Long id);
    ProfesionalRespuestaDTO buscarPorIdProfesional(Long id);
    UsuarioRespuestaDTO actualizar(Long id, UsuarioDTO usuarioDTO);
    ProfesionalRespuestaDTO actualizarProfesional(Long id, ProfesionalDTO profesionalDTO);
    List<UsuarioRespuestaDTO> buscarTodos();
    List<ProfesionalRespuestaDTO> buscarTodosProfesionales();
    void enviarMensajeProveedor (Long idProveedor, MensajeDTO dto);
}