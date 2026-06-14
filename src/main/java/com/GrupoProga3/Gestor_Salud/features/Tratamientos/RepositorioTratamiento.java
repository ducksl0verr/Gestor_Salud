package com.GrupoProga3.Gestor_Salud.features.Tratamientos;

import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.EntidadTratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTratamiento extends JpaRepository<EntidadTratamiento, Long> {
}
