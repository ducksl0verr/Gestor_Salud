package com.GrupoProga3.Gestor_Salud.features.Proveedores;

import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.EntidadProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProveedor extends JpaRepository<EntidadProveedor, Long> {
}
