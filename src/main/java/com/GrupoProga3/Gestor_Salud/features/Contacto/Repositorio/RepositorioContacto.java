package com.GrupoProga3.Gestor_Salud.features.Contacto.Repositorio;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioContacto
        extends JpaRepository<EntidadContacto, Long> {

    boolean existsByTelefono(String telefono);

    boolean existsByEmail(String email);

    boolean existsByTelefonoAndIdNot(String telefono, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}
