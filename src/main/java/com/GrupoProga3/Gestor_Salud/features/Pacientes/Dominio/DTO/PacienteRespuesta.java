package com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.EntidadPrescripcionTratamiento;

import java.time.LocalDate;
import java.util.List;

public record PacienteRespuesta(Long id,
                                String nombre,
                                String apellido,
                                LocalDate fecha_nacimiento,
                                String dni,
                                Long numeroAfiliado,
                                ContactoRespuesta contacto,
                                DomicilioRespuesta domicilio,
                                Long id_obraSocial,
                                String nombreObraSocial,
                                String coberturaObraSocial,
                                List<EntidadPrescripcionTratamiento> tratamientos) {
}
