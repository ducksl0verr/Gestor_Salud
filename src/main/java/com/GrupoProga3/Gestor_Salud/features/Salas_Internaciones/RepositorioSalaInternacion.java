package com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones;

import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.EntidadSalaInternacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioSalaInternacion extends JpaRepository<EntidadSalaInternacion, Long> {
}
