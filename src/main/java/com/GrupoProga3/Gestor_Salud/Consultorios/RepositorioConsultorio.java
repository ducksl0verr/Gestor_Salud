package com.GrupoProga3.Gestor_Salud.Consultorios;

import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.EntidadConsultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioConsultorio extends JpaRepository<EntidadConsultorio, Long> {
}
