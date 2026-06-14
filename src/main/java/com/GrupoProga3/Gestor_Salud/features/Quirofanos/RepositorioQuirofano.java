package com.GrupoProga3.Gestor_Salud.features.Quirofanos;

import com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.EntidadQuirofano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioQuirofano extends JpaRepository<EntidadQuirofano, Long> {
}
