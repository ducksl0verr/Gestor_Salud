package com.GrupoProga3.Gestor_Salud.features.Especialidades;

import com.GrupoProga3.Gestor_Salud.features.Especialidades.Dominio.EntidadEspecialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEspecialidad extends JpaRepository<EntidadEspecialidad, Long> {
}
