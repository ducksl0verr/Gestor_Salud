package com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO;


import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;

public record UsuarioRespuestaDTO(Long idUsuario,
                                  String nombre,
                                  String apellido,
                                  String dni,
                                  ContactoRespuesta contacto) {}
