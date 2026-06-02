package com.GrupoProga3.Gestor_Salud.ObraSocial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioObraSocial extends JpaRepository<EntidadObraSocial, Long> {
}
