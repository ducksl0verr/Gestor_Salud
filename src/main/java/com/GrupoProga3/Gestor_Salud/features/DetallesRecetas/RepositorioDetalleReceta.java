package com.GrupoProga3.Gestor_Salud.features.DetallesRecetas;

import com.GrupoProga3.Gestor_Salud.features.DetallesRecetas.Dominio.EntidadDetalleReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDetalleReceta extends JpaRepository<EntidadDetalleReceta, Long> {
}
