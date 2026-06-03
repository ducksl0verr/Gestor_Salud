package com.GrupoProga3.Gestor_Salud.Usuarios.Servicio;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.Mappers.UsuarioMapper;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.Usuarios.Repositorio.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // solo genera constructor a aquellos atributos final
public class ServicioUsuario implements IServicioUsuario {

    private final RepositorioUsuario repositorioUsuario;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO guardar(UsuarioDTO usuarioDTO) {
        EntidadUsuarios guardado = repositorioUsuario.save(usuarioMapper.ToEntity(usuarioDTO));
        return usuarioMapper.ToDto(guardado);
    }

    @Override
    public ProfesionalDTO guardarProfesional(ProfesionalDTO profesionalDTO) {
        EntidadUsuarios guardado = repositorioUsuario.save(usuarioMapper.ProfToEntity(profesionalDTO));
        return usuarioMapper.ProfToDTO(guardado);
    }

    @Override
    public void borrar(Long id) {
        this.repositorioUsuario.findById(id).ifPresent(repositorioUsuario::delete);
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        return repositorioUsuario.findById(id)
                .map(usuarioMapper::ToDto)
                .orElseThrow();
    }

    @Override
    public ProfesionalDTO buscarPorIdProfesional(Long id) {
        return repositorioUsuario.findById(id)
                .map(usuarioMapper::ProfToDTO)
                .orElseThrow();
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO) {

        EntidadUsuarios usu = repositorioUsuario.findById(id)
                .orElseThrow();
        usu.setNombre(usuarioDTO.nombre());
        usu.setApellido(usuarioDTO.apellido());
        usu.setDni(usuarioDTO.dni());
        usu.setEmail(usuarioDTO.email());
        usu.setTelefono(usuarioDTO.telefono());

        EntidadUsuarios actualizado = repositorioUsuario.save(usu);

        return usuarioMapper.ToDto(usu);
    }

    @Override
    public ProfesionalDTO actualizarProfesional(Long id, ProfesionalDTO profesionalDTO) {
        EntidadUsuarios usu = repositorioUsuario.findById(id)
                .orElseThrow();
        usu.setNombre(profesionalDTO.nombre());
        usu.setApellido(profesionalDTO.apellido());
        usu.setDni(profesionalDTO.dni());
        usu.setEmail(profesionalDTO.email());
        usu.setTelefono(profesionalDTO.telefono());

        EntidadUsuarios actualizado = repositorioUsuario.save(usu);

        return usuarioMapper.ProfToDTO(actualizado);
    }

    @Override
    public List<UsuarioDTO> buscarTodos() {
        return repositorioUsuario.findAll().stream().map(usuarioMapper::ToDto).toList();
    }

    @Override
    public List<ProfesionalDTO> buscarTodosProfesionales() {
        return repositorioUsuario.findAll().stream().map(usuarioMapper::ProfToDTO).toList();
    }
}
