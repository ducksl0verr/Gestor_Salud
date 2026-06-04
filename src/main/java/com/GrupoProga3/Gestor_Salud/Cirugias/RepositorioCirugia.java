package com.GrupoProga3.Gestor_Salud.Cirugias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCirugia extends JpaRepository<EntidadCirugia, Long> {
}
