package com.GrupoProga3.Gestor_Salud.Domicilio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioDomicilio extends JpaRepository<EntidadDomicilio, Long> {
    //Optional<EntidadDomicilio> buscarPorId(Long id); IGNORAR ESTO, DANTE ESTABA INTENTANDO ALGO
    // (osea yo, juas juas)
}
