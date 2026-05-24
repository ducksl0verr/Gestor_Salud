package com.GrupoProga3.Gestor_Salud.Pacientes.RepositorioPaciente;

import com.GrupoProga3.Gestor_Salud.Pacientes.EntidadPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPaciente extends JpaRepository<EntidadPaciente,Long> {
}
