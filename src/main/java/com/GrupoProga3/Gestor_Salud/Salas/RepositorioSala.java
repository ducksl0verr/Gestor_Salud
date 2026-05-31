package com.GrupoProga3.Gestor_Salud.Salas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioSala extends JpaRepository<EntidadSala, Long> {
}
