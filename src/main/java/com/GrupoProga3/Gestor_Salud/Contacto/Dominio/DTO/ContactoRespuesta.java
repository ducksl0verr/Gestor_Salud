package com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;

public record ContactoRespuesta(

        Long id,
        String nombre,
        String apellido,
        String telefono,
        Long idPaciente,
        PacienteRespuesta paciente

) {
}
