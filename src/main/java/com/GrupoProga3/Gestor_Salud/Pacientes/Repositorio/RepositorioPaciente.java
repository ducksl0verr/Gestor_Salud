package com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio;

import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPaciente extends JpaRepository<EntidadPaciente,Long> {
}
