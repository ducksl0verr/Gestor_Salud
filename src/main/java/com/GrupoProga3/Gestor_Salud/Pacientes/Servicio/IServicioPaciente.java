package com.GrupoProga3.Gestor_Salud.Pacientes.Servicio;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteNuevo;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;

import java.util.List;

public interface IServicioPaciente {

    PacienteRespuesta guardar (PacienteNuevo pacienteNuevo);
    void borrar(Long id);
    PacienteRespuesta actualizar(Long id, PacienteNuevo pacienteNuevo);
    PacienteRespuesta buscarPorid (Long id);
    List<PacienteRespuesta> buscarTodos();
}
