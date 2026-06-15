package com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialRespuesta;
import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.EntidadPrescripcionTratamiento;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionRespuesta;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record PacienteRespuesta(Long id,
                                String nombre,
                                String apellido,
                                LocalDate fecha_nacimiento,
                                String dni,
                                Long numeroAfiliado,
                                DomicilioRespuesta domicilio,
                                Long id_obraSocial,
                                String nombreObraSocial,
                                String coberturaObraSocial,
                                List<EntidadPrescripcionTratamiento> tratamientos,
                                SalaInternacionRespuesta salaInternacion) {
}
