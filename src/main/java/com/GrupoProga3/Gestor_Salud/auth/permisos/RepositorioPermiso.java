package com.GrupoProga3.Gestor_Salud.auth.permisos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioPermiso extends JpaRepository<EntidadPermiso,Long> {
    Optional<EntidadPermiso> findByPermiso(PERMISOS permiso);
}
