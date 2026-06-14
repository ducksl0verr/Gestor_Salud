package com.GrupoProga3.Gestor_Salud.features.Medicamentos;

import com.GrupoProga3.Gestor_Salud.features.Medicamentos.Dominio.EntidadMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RepositorioMedicamento extends JpaRepository<EntidadMedicamento, Long> {
    @Modifying
    @Query("""
            DELETE FROM EntidadMedicamento m
            WHERE m.fechaVencimiento < : fecha""")
    void deleteVencidos(@Param("fecha") LocalDate fecha);
}
