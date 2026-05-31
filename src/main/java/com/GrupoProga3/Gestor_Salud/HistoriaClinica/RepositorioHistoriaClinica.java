package com.GrupoProga3.Gestor_Salud.HistoriaClinica;

import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.EntidadHistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioHistoriaClinica extends JpaRepository<EntidadHistoriaClinica, Long> {
}
