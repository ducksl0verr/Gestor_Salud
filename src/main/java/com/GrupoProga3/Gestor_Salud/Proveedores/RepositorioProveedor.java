package com.GrupoProga3.Gestor_Salud.Proveedores;

import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.EntidadProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioProveedor extends JpaRepository<EntidadProveedor, Long> {
    Optional<EntidadProveedor> buscarPorNombre(String nombre);
}
