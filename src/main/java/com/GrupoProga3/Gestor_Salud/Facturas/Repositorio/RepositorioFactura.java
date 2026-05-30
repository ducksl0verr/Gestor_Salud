package com.GrupoProga3.Gestor_Salud.Facturas.Repositorio;

import com.GrupoProga3.Gestor_Salud.Facturas.Model.EntidadFacturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioFactura extends JpaRepository<EntidadFacturas,Long> {
}
