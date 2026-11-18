package com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PacienteNuevo(@NotBlank(message = "El nombre es obligatorio")
                            @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
                            String nombre,
                            @NotBlank(message = "El apellido es obligatorio")
                            @Size(max = 50, message = "El apellido no puede superar los 50 caracteres")
                            String apellido,
                            @NotNull(message = "La fecha de nacimiento es obligatoria")
                            @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
                            LocalDate fecha_nacimiento,
                            @NotBlank(message = "El DNI es obligatorio")
                            @Size(max = 20, message = "El DNI no puede superar los 20 caracteres")
                            String dni,
                            @NotNull(message = "El número de afiliado es obligatorio")
                            @Positive(message = "El número de afiliado debe ser mayor a 0")
                            Long numeroAfiliado,
                            @NotNull(message = "El domicilio es obligatorio")
                            @Valid DomicilioNuevo domicilio,
                            @NotNull(message = "La obra social es obligatoria")
                            @Positive(message = "El ID de la obra social debe ser mayor a 0")
                            Long id_obraSocial,
                            @NotNull(message = "El contacto es obligatorio")
                            @Valid ContactoNuevo contacto) {}
