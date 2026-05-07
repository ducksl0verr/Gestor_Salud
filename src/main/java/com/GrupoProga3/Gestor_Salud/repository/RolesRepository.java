package com.GrupoProga3.Gestor_Salud.repository;

import com.GrupoProga3.Gestor_Salud.entity.Roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
