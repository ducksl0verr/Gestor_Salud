package com.GrupoProga3.Gestor_Salud.Usuarios.Repositorio;

import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<EntidadUsuarios,Long> {
}
