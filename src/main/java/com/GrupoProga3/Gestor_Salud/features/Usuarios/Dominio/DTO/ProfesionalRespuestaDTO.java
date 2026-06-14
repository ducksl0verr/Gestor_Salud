package com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;

public record ProfesionalRespuestaDTO(Long idUsuarioProfesional,
                                      String nombre,
                                      String apellido,
                                      String dni,
                                      String matricula,
                                      ContactoRespuesta contacto) {
}
