package com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.ObraSocialRespuesta;
import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.EntidadPrescripcionTratamiento;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.DTOs.TratamientoRespuesta;

import java.time.LocalDate;
import java.util.List;

public record PacienteRespuesta(Long id,
                                String nombre,
                                String apellido,
                                LocalDate fecha_nacimiento,
                                DomicilioRespuesta domicilio,
                                Long id_obraSocial,
                                List<EntidadPrescripcionTratamiento> tratamientos) {
}
