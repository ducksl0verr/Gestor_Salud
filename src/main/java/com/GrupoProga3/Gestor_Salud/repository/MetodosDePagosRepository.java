package com.GrupoProga3.Gestor_Salud.repository;

import com.GrupoProga3.Gestor_Salud.entity.MetodosDePagos.MetodosDePagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodosDePagosRepository extends JpaRepository<MetodosDePagos, Long> {
}
