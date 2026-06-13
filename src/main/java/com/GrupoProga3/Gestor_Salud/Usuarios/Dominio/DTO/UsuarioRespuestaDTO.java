package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO;


import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoRespuesta;

public record UsuarioRespuestaDTO(Long idUsuario,
                                  String nombre,
                                  String apellido,
                                  String dni,
                                  ContactoRespuesta contacto) {}
