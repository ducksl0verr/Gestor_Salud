package com.GrupoProga3.Gestor_Salud.repository;

import com.GrupoProga3.Gestor_Salud.entity.Proveedores.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores, Long> {
}
