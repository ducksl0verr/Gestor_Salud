package com.GrupoProga3.Gestor_Salud.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<EntidadUsuarios, Long> {


}
