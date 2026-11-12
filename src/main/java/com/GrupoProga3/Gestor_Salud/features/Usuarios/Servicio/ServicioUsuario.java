package com.GrupoProga3.Gestor_Salud.features.Usuarios.Servicio;

import com.GrupoProga3.Gestor_Salud.auth.DTOs.CuentaNueva;
import com.GrupoProga3.Gestor_Salud.auth.credenciales.EntidadCredencial;
import com.GrupoProga3.Gestor_Salud.auth.credenciales.RepositorioCredencial;
import com.GrupoProga3.Gestor_Salud.auth.permisos.EntidadRole;
import com.GrupoProga3.Gestor_Salud.auth.permisos.RepositorioRole;
import com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion.RepositorioTokenRecuperacion;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.features.Notificaciones.MensajeDTO;
import com.GrupoProga3.Gestor_Salud.features.Notificaciones.ServicioEmail;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.EntidadProveedor;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.RepositorioProveedor;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.ProfesionalRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO.UsuarioRespuestaDTO;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.Mappers.UsuarioMapper;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Repositorio.RepositorioUsuario;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor // solo genera constructor a aquellos atributos final
  public class ServicioUsuario implements IServicioUsuario {

    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioRole repositorioRole;
    private final RepositorioProveedor repositorioProveedor;
    private final UsuarioMapper usuarioMapper;
    private final ServicioEmail email;
    private final PasswordEncoder passwordEncoder;
    private final RepositorioCredencial repositorioCredencial;
    private final RepositorioTokenRecuperacion repositorioTokenRecuperacion;

    @Override
    @Transactional
    public UsuarioRespuestaDTO guardar(CuentaNueva nueva) {
        EntidadUsuarios user = usuarioMapper.ToEntity(nueva);
        normalizarYValidarEmail(user);

        EntidadRole role = repositorioRole
                .findByRole(nueva.role())
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Role",
                        "No encontrado",
                        null,
                        "No se ha encontrado ningún rol llamado: "+ nueva.role()
                ));

        user.setRole(role);

        EntidadUsuarios guardado = repositorioUsuario.save(user);

        EntidadCredencial credencial = EntidadCredencial.builder()
                .username(nueva.username())
                .password(passwordEncoder.encode(nueva.password()))
                .enabled(true)
                .usuario(guardado)
                .role(role)
                .build();

        repositorioCredencial.save(credencial);

        return usuarioMapper.toRespuestaUsuarioDTO(guardado);
    }

    @Override
    @Transactional
    public ProfesionalRespuestaDTO guardarProfesional(CuentaNueva nueva) {

        EntidadUsuarios user = usuarioMapper.ToEntity(nueva);
        normalizarYValidarEmail(user);

        EntidadRole role = repositorioRole
                .findByRole(nueva.role())
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Role",
                        "No encontrado",
                        null,
                        "No se ha encontrado ningún rol llamado: "+ nueva.role()
                ));

        user.setRole(role);

        EntidadUsuarios guardado = repositorioUsuario.save(user);

        EntidadCredencial credencial = EntidadCredencial.builder()
                .username(nueva.username())
                .password(passwordEncoder.encode(nueva.password()))
                .enabled(true)
                .usuario(guardado)
                .role(role)
                .build();

        repositorioCredencial.save(credencial);

        return usuarioMapper.toRespuestaProfesionalDTO(guardado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        EntidadUsuarios user = repositorioUsuario.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Usuario",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún usuario con aquel id."));

        EntidadCredencial crede =repositorioCredencial.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("Credencial",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado una credencial con aquel id."));

        this.repositorioTokenRecuperacion.borrarPorUsuario(user);
        this.repositorioCredencial.delete(crede);
        this.repositorioUsuario.delete(user);
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
        usu.setContacto(usuarioMapper.toEntity(usuarioDTO.contacto()));
        normalizarYValidarEmail(usu, usu.getId());

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
        usu.setContacto(usuarioMapper.toEntity(profesionalDTO.contacto()));
        normalizarYValidarEmail(usu, usu.getId());

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

    @Override
    public void enviarMensajeProveedor(Long id, MensajeDTO dto) {
        EntidadProveedor p = repositorioProveedor
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Proveedor",
                        "No encontrado",
                        id,
                        "No se ha encontrado ningún proveedor con aquel ID."
                ));
        email.enviarMail(p.getContacto().getEmail(), dto.asunto(), dto.mensaje());
    }

    /**
     * Normaliza el email y evita que se registren dos cuentas con el mismo
     * (necesario para que la recuperación de contraseña identifique una única cuenta).
     */
    private void normalizarYValidarEmail(EntidadUsuarios user) {
        normalizarYValidarEmail(user, null);
    }

    private void normalizarYValidarEmail(EntidadUsuarios user, Long idExcluido) {
        if (user.getContacto() == null || user.getContacto().getEmail() == null) {
            return;
        }

        String email = user.getContacto().getEmail().trim().toLowerCase(Locale.ROOT);
        user.getContacto().setEmail(email);

        boolean ocupado = repositorioUsuario.findAllByContactoEmail(email)
                .stream()
                .anyMatch(otro -> idExcluido == null || !idExcluido.equals(otro.getId()));

        if (ocupado) {
            throw new RecursoOcupadoException(
                    "Ya existe una cuenta registrada con el email " + email + ".");
        }
    }
}
