package com.GrupoProga3.Gestor_Salud.features.Consultorios;

import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.EntidadConsultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioConsultorio extends JpaRepository<EntidadConsultorio, Long> {
}
