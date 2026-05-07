package com.GrupoProga3.Gestor_Salud.repository;

import com.GrupoProga3.Gestor_Salud.entity.Salas.Salas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalasRepository extends JpaRepository<Salas, Long> {
}
