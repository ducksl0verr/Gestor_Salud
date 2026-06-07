package com.GrupoProga3.Gestor_Salud.Medicamentos;

import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoActualizar;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoNuevo;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoRespuesta;

import java.util.List;

public interface IServicioMedicamento {

    MedicamentoRespuesta crear (MedicamentoNuevo medicamentoNuevo);
    MedicamentoRespuesta buscarPorId (Long id);
    MedicamentoRespuesta buscarPorNombre (String nombre);
    List<MedicamentoRespuesta> buscarTodos();
    List<MedicamentoRespuesta> buscarPorPrincipioActivo(String principioActivo);
    MedicamentoRespuesta actualizar (Long id, MedicamentoActualizar medicamentoActualizar);
}
