package com.GrupoProga3.Gestor_Salud.Medicamentos;

import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.EntidadMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositorioMedicamento extends JpaRepository<EntidadMedicamento, Long> {
    @Modifying
    @Query("""
            DELETE FROM EntidadMedicamento m
            WHERE m.fechaVencimiento < : fecha""")
    void deleteVencidos(@Param("fecha") LocalDate fecha);
}
