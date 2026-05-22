package com.GrupoProga3.Gestor_Salud.Usuarios;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import java.util.List;

public interface IServicioUsuario {
    UsuarioDTO guardar (UsuarioDTO usuarioDTO);
    void borrar (Long id);
    UsuarioDTO buscarPorId(Long id);
    UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO);
    List<UsuarioDTO> buscarTodos();
}