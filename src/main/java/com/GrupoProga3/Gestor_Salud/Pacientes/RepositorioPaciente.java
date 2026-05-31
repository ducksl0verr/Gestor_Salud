package com.GrupoProga3.Gestor_Salud.Pacientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPaciente extends JpaRepository<EntidadPaciente, Long> {
}
