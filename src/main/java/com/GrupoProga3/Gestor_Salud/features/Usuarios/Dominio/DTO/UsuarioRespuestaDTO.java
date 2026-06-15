package com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO;


import com.GrupoProga3.Gestor_Salud.auth.permisos.EntidadRole;
import com.GrupoProga3.Gestor_Salud.auth.permisos.ROLES;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRespuestaDTO(Long idUsuario,
                                  String nombre,
                                  String apellido,
                                  String dni,
                                  ContactoRespuesta contacto,
                                  String username,
                                  String password,
                                  ROLES role) {}
