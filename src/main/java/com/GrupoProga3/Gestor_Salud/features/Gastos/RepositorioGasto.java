package com.GrupoProga3.Gestor_Salud.features.Gastos;

import com.GrupoProga3.Gestor_Salud.features.Gastos.Dominio.EntidadGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioGasto extends JpaRepository<EntidadGasto, Long> {
}
