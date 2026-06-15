package com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.auth.permisos.EntidadRole;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfesionalRespuestaDTO(Long idUsuarioProfesional,
                                      String nombre,
                                      String apellido,
                                      String dni,
                                      String matricula,
                                      ContactoRespuesta contacto,
                                      @NotBlank String username, @NotBlank String password,
                                      @NotNull EntidadRole role) {
}
