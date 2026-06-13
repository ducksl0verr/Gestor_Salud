package com.GrupoProga3.Gestor_Salud.Usuarios.Servicio;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.Mappers.UsuarioMapper;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.Usuarios.Repositorio.RepositorioUsuario;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // solo genera constructor a aquellos atributos final
  public class ServicioUsuario implements IServicioUsuario {

    private final RepositorioUsuario repositorioUsuario;
    private final UsuarioMapper usuarioMapper;

    @Override
    @Transactional
    public UsuarioRespuestaDTO guardar(UsuarioDTO usuarioDTO) {
        EntidadUsuarios guardado = repositorioUsuario.save(usuarioMapper.ToEntity(usuarioDTO));
        return usuarioMapper.toRespuestaUsuarioDTO(guardado);
    }

    @Override
    @Transactional
    public ProfesionalRespuestaDTO guardarProfesional(ProfesionalDTO profesionalDTO) {
        EntidadUsuarios guardado = repositorioUsuario.save(usuarioMapper.ProfToEntity(profesionalDTO));
        return usuarioMapper.toRespuestaProfesionalDTO(guardado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        this.repositorioUsuario.findById(id).ifPresent(repositorioUsuario::delete);
    }

    @Override
    public UsuarioRespuestaDTO buscarPorId(Long id) {
        return repositorioUsuario.findById(id)
                .map(usuarioMapper::toRespuestaUsuarioDTO)
                .orElseThrow(()-> new EntidadNoEncontradaException("Usuario",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún usuario con aquel ID."));
    }

    @Override
    public ProfesionalRespuestaDTO buscarPorIdProfesional(Long id) {
        return repositorioUsuario.findById(id)
                .map(usuarioMapper::toRespuestaProfesionalDTO)
                .orElseThrow(()-> new EntidadNoEncontradaException("Profesional",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún profesional con aquel ID."));
    }

    @Override
    @Transactional
    public UsuarioRespuestaDTO actualizar(Long id, UsuarioDTO usuarioDTO) {
        EntidadUsuarios usu = repositorioUsuario.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Usuario",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún usuario con aquel ID."));

        usu.setNombre(usuarioDTO.nombre());
        usu.setApellido(usuarioDTO.apellido());
        usu.setDni(usuarioDTO.dni());
        usu.setContacto(usuarioMapper.toEntity(usuarioDTO.contacto()));

        EntidadUsuarios actualizado = repositorioUsuario.save(usu);

        return usuarioMapper.toRespuestaUsuarioDTO(actualizado);
    }

    @Override
    @Transactional
    public ProfesionalRespuestaDTO actualizarProfesional(Long id, ProfesionalDTO profesionalDTO) {
        EntidadUsuarios usu = repositorioUsuario.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Profesional",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún profesional con aquel ID."
                ));
        usu.setNombre(profesionalDTO.nombre());
        usu.setApellido(profesionalDTO.apellido());
        usu.setDni(profesionalDTO.dni());
        usu.setContacto(usuarioMapper.toEntity(profesionalDTO.contacto()));

        EntidadUsuarios actualizado = repositorioUsuario.save(usu);

        return usuarioMapper.toRespuestaProfesionalDTO(actualizado);
    }

    @Override
    public List<UsuarioRespuestaDTO> buscarTodos() {
        return repositorioUsuario.findAll()
                .stream()
                .filter(u->u.getMatricula() == null
                || u.getMatricula().isBlank())
                .map(usuarioMapper::toRespuestaUsuarioDTO)
                .toList();
    }

    @Override
    public List<ProfesionalRespuestaDTO> buscarTodosProfesionales() {
        return repositorioUsuario.findAll()
                .stream()
                .filter(p-> p.getMatricula() != null
                && !p.getMatricula().isBlank())
                .map(usuarioMapper::toRespuestaProfesionalDTO)
                .toList();
    }
}
