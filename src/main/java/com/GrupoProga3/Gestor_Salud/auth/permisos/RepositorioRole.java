package com.GrupoProga3.Gestor_Salud.auth.permisos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRole extends JpaRepository<EntidadRole, Long> {
}
