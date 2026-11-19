package com.GrupoProga3.Gestor_Salud.features.ObraSocial;

import com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.EntidadObraSocial;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Crea la obra social predeterminada "Sin obra social" para que todo
 * paciente pueda registrarse aunque no tenga cobertura.
 */
@Component
@RequiredArgsConstructor
@Transactional
public class EstablecedorObraSocial implements CommandLineRunner {

    private static final String COBERTURA_SIN_OBRA = "Sin cobertura";

    private final RepositorioObraSocial repositorioObraSocial;

    @Override
    public void run(String... args) {

        if (Boolean.FALSE.equals(
                repositorioObraSocial.existsByNombre(ObraSocialConstantes.NOMBRE_SIN_OBRA_SOCIAL))) {

            EntidadObraSocial sinObra = EntidadObraSocial.builder()
                    .nombre(ObraSocialConstantes.NOMBRE_SIN_OBRA_SOCIAL)
                    .cobertura(COBERTURA_SIN_OBRA)
                    .domicilios(new ArrayList<>())
                    .build();

            repositorioObraSocial.save(sinObra);
        }
    }
}
