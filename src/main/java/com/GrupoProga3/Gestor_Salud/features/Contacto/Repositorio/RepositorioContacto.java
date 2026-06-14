package com.GrupoProga3.Gestor_Salud.features.Contacto.Repositorio;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioContacto
        extends JpaRepository<EntidadContacto, Long> {

    boolean existsByTelefono(String telefono);

    boolean existsByEmail(String email);

    /*List<EntidadContacto> findByNombreContainingAndApellidoContaining(
            String nombre,
            String apellido
    );

    List<EntidadContacto> findByNombreContaining(String nombre);

    List<EntidadContacto> findByApellidoContaining(String apellido);

     */

}


