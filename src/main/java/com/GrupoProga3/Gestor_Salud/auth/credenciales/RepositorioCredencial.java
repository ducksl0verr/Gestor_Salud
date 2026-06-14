package com.GrupoProga3.Gestor_Salud.auth.credenciales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioCredencial extends JpaRepository<EntidadCredencial,Long> {
    Optional<EntidadCredencial> findByUsername(String us);
}
