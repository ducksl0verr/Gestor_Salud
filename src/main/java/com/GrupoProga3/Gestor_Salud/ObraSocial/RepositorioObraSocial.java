package com.GrupoProga3.Gestor_Salud.ObraSocial;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Arrays;
import java.util.List;

@Repository
public interface RepositorioObraSocial extends JpaRepository<EntidadObraSocial, Long> {


    Boolean existsByNombre( String nombre);

    List<EntidadObraSocial> findByNombreContainingAndCoberturaContaining(String nombre, String cobertura);

    List<EntidadObraSocial> findByNombreContaining(String nombre);

    List<EntidadObraSocial> findByCoberturaContaining(String cobertura);


}
