package com.GrupoProga3.Gestor_Salud.DetallesRecetas;

import com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.EntidadDetalleReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDetalleReceta extends JpaRepository<EntidadDetalleReceta, Long> {
}
