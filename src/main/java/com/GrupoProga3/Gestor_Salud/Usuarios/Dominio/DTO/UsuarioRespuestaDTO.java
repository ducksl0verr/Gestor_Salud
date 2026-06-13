package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO;



public record UsuarioRespuestaDTO(Long idUsuario,
                                   String nombre,
                                   String apellido,
                                   String dni,
                                   String telefono,
                                   String email) {}
