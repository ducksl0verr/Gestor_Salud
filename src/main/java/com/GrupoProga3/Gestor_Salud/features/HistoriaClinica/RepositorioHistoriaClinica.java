package com.GrupoProga3.Gestor_Salud.features.HistoriaClinica;

import com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.EntidadHistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioHistoriaClinica extends JpaRepository<EntidadHistoriaClinica, Long> {
}
