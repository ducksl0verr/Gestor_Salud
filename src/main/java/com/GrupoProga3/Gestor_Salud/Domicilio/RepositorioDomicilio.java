package com.GrupoProga3.Gestor_Salud.Domicilio;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDomicilio extends JpaRepository<EntidadDomicilio, Long> {
}