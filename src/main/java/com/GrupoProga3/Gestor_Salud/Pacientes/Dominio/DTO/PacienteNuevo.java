package com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialNueva;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public record PacienteNuevo(@NotBlank String nombre,
                            @NotBlank String apellido,
                            @NotNull(message = "La fecha de nacimiento es obligatoria")
                            @Past(message = "La fecha debe ser anterior a hoy")
                            LocalDate fecha_nacimiento,
                            @NotBlank
                            String dni,
                            @NotNull
                            Long numeroAfiliado,
                            @Valid DomicilioNuevo domicilio,
                            @Valid Long id_obraSocial) {}