package com.GrupoProga3.Gestor_Salud.Medicamentos;

import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.EntidadMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMedicamento extends JpaRepository<EntidadMedicamento, Long> {
}
