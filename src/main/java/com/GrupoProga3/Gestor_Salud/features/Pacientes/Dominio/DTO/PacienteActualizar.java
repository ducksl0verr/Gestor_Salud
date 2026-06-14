package com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteActualizar(@NotBlank
                                 String nombre,
                                 @NotBlank
                                 String apellido,
                                 //LocalDate fecha_nacimiento,
                                 @NotBlank
                                 String dni,
                                 @NotNull
                                 Long numeroAfiliado,
                                 @NotNull
                                 DomicilioNuevo domicilio,
                                 @NotNull
                                 Long id_obraSocial,
                                 @NotNull
                                 ContactoNuevo contacto) {
}
