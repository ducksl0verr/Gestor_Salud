package com.GrupoProga3.Gestor_Salud.features.Pacientes.Servicio;

import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteActualizar;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteNuevo;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteRespuesta;

import java.util.List;

public interface IServicioPaciente {

    PacienteRespuesta guardar (PacienteNuevo pacienteNuevo);
    void borrar(Long id);
    PacienteRespuesta actualizar(Long id, PacienteActualizar pacienteActualizar);
    PacienteRespuesta buscarPorid (Long id);
    List<PacienteRespuesta> buscarTodos();
}
