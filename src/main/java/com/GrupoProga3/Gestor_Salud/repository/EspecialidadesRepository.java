package com.GrupoProga3.Gestor_Salud.repository;

import com.GrupoProga3.Gestor_Salud.entity.Especialidades.Especialidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadesRepository extends JpaRepository<Especialidades, Long> {
}
