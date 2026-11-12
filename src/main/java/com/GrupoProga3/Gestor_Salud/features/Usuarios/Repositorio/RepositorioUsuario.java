package com.GrupoProga3.Gestor_Salud.features.Usuarios.Repositorio;

import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioUsuario extends JpaRepository<EntidadUsuarios,Long> {
    List<EntidadUsuarios> findAllByContactoEmail(String email);

    boolean existsByContactoEmail(String email);
}
