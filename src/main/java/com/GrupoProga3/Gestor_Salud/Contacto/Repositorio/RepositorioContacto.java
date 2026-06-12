package com.GrupoProga3.Gestor_Salud.Contacto.Repositorio;

import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface RepositorioContacto
        extends JpaRepository<EntidadContacto, Long> {

    boolean existsByTelefono(String telefono);

    boolean existsByPacienteId(Long idPaciente);

    List<EntidadContacto> findByNombreContainingAndApellidoContaining(
            String nombre,
            String apellido
    );

    List<EntidadContacto> findByNombreContaining(String nombre);

    List<EntidadContacto> findByApellidoContaining(String apellido);
}

