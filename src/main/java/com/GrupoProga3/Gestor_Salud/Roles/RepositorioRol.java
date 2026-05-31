package com.GrupoProga3.Gestor_Salud.Roles;

import com.GrupoProga3.Gestor_Salud.Roles.Dominio.EntidadRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRol extends JpaRepository<EntidadRol, Long> {
}
