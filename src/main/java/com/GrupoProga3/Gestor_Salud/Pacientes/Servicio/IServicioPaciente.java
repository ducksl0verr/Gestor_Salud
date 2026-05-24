package com.GrupoProga3.Gestor_Salud.Pacientes.Servicio;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteDTO;

import java.util.List;

public interface IServicioPaciente {

    PacienteDTO guardar (PacienteDTO pacienteDTO);
    void borrar(Long id);
    PacienteDTO actualizar(Long id, PacienteDTO pacienteDTO);
    PacienteDTO buscarPorid (Long id);
    List<PacienteDTO> buscarTodos();
}
