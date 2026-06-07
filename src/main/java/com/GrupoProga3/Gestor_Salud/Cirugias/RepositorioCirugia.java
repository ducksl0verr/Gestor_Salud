package com.GrupoProga3.Gestor_Salud.Cirugias;

import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.EntidadCirugia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCirugia extends JpaRepository<EntidadCirugia, Long> {
}
