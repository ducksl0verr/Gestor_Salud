package com.GrupoProga3.Gestor_Salud.features.DetalleFacturas.Repositorio;
import com.GrupoProga3.Gestor_Salud.features.DetalleFacturas.Model.EntidadDetalleFacturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDetalleFactura extends JpaRepository<EntidadDetalleFacturas, Long> {
}
