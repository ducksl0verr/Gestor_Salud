package com.GrupoProga3.Gestor_Salud.Turno;

import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTurno extends JpaRepository<EntidadTurno, Long> {
}
