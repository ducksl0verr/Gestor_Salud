package com.GrupoProga3.Gestor_Salud.Contacto.Repositorio;

import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioContacto extends JpaRepository <EntidadContacto, Long> {
}
