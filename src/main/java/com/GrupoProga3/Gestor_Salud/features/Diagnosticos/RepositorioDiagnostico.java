package com.GrupoProga3.Gestor_Salud.features.Diagnosticos;

import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.EntidadDiagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDiagnostico extends JpaRepository<EntidadDiagnostico,Long> {
}
