package com.GrupoProga3.Gestor_Salud.features.Recetas;

import com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.EntidadReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioReceta extends JpaRepository<EntidadReceta, Long> {
}
