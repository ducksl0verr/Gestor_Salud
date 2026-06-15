package com.GrupoProga3.Gestor_Salud.auth.permisos;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioRole extends JpaRepository<EntidadRole, Long> {
    @EntityGraph(attributePaths = "permisos")
    Optional<EntidadRole> findByRole(ROLES role);
}
