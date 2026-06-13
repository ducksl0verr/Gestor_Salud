package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO;

public record ProfesionalRespuestaDTO(Long idUsuarioProfesional,
                                      String nombre,
                                      String apellido,
                                      String dni,
                                      String telefono,
                                      String matricula,
                                      String email) {
}
