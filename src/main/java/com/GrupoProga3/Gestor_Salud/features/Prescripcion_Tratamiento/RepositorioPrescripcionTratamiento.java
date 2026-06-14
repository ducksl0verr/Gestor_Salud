package com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento;

import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.EntidadPrescripcionTratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPrescripcionTratamiento extends JpaRepository<EntidadPrescripcionTratamiento, Long> {
    boolean existsByPacienteAndTratamientosAndActivoTrue(Long pacienteId, Long tratamientoId);
}
