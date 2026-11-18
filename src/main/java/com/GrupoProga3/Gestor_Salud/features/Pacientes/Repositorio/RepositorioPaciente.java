package com.GrupoProga3.Gestor_Salud.features.Pacientes.Repositorio;

import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPaciente extends JpaRepository<EntidadPaciente, Long> {

    boolean existsByDni(String dni);

    boolean existsByNumeroAfiliado(Long numeroAfiliado);

    boolean existsByDniAndIdNot(String dni, Long id);

    boolean existsByNumeroAfiliadoAndIdNot(Long numeroAfiliado, Long id);
}
