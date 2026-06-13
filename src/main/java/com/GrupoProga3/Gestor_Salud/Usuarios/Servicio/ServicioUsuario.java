package com.GrupoProga3.Gestor_Salud.Usuarios.Servicio;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
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
    public UsuarioRespuestaDTO guardar(UsuarioDTO usuarioDTO) {
        EntidadUsuarios guardado = repositorioUsuario.save(usuarioMapper.ToEntity(usuarioDTO));
        return usuarioMapper.toRespuestaUsuarioDTO(guardado);
    }

    @Override
    public ProfesionalRespuestaDTO guardarProfesional(ProfesionalDTO profesionalDTO) {
        EntidadUsuarios guardado = repositorioUsuario.save(usuarioMapper.ProfToEntity(profesionalDTO));
        return usuarioMapper.toRespuestaProfesionalDTO(guardado);
    }

    @Override
    public void borrar(Long id) {
        this.repositorioUsuario.findById(id).ifPresent(repositorioUsuario::delete);
    }

    @Override
    public UsuarioRespuestaDTO buscarPorId(Long id) {
        return repositorioUsuario.findById(id)
                .map(usuarioMapper::toRespuestaUsuarioDTO)
                .orElseThrow();
    }

    @Override
    public ProfesionalRespuestaDTO buscarPorIdProfesional(Long id) {
        return repositorioUsuario.findById(id)
                .map(usuarioMapper::toRespuestaProfesionalDTO)
                .orElseThrow();
    }

    @Override
    public UsuarioRespuestaDTO actualizar(Long id, UsuarioDTO usuarioDTO) {

        EntidadUsuarios usu = repositorioUsuario.findById(id)
                .orElseThrow();
        usu.setNombre(usuarioDTO.nombre());
        usu.setApellido(usuarioDTO.apellido());
        usu.setDni(usuarioDTO.dni());
        usu.setEmail(usuarioDTO.email());
        usu.setTelefono(usuarioDTO.telefono());

        EntidadUsuarios actualizado = repositorioUsuario.save(usu);

        return usuarioMapper.toRespuestaUsuarioDTO(usu);
    }

    @Override
    public ProfesionalRespuestaDTO actualizarProfesional(Long id, ProfesionalDTO profesionalDTO) {
        EntidadUsuarios usu = repositorioUsuario.findById(id)
                .orElseThrow();
        usu.setNombre(profesionalDTO.nombre());
        usu.setApellido(profesionalDTO.apellido());
        usu.setDni(profesionalDTO.dni());
        usu.setEmail(profesionalDTO.email());
        usu.setTelefono(profesionalDTO.telefono());

        EntidadUsuarios actualizado = repositorioUsuario.save(usu);

        return usuarioMapper.toRespuestaProfesionalDTO(actualizado);
    }

    @Override
    public List<UsuarioRespuestaDTO> buscarTodos() {
        return repositorioUsuario.findAll()
                .stream()
                .filter(usuario -> usuario.getMatricula() == null
                        || usuario.getMatricula().isBlank())
                .map(usuarioMapper::toRespuestaUsuarioDTO)
                .toList();
    }

    @Override
    public List<ProfesionalRespuestaDTO> buscarTodosProfesionales() {
        return repositorioUsuario.findAll()
                .stream()
                .filter(usuario -> usuario.getMatricula() != null
                        && !usuario.getMatricula().isBlank())
                .map(usuarioMapper::toRespuestaProfesionalDTO)
                .toList();
    }
}
