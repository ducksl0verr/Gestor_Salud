package com.GrupoProga3.Gestor_Salud.features.Turno;

import com.GrupoProga3.Gestor_Salud.auth.credenciales.EntidadCredencial;
import com.GrupoProga3.Gestor_Salud.auth.credenciales.RepositorioCredencial;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.ReglaNegocioException;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.EntidadConsultorio;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.RepositorioConsultorio;
import com.GrupoProga3.Gestor_Salud.features.Notificaciones.ServicioEmail;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.RepositorioTratamiento;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.DisponibilidadTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoFacturable;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs.TurnoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.EntidadTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.Mapper.TurnoMapper;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Repositorio.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioTurno implements IServicioTurno {

    private static final LocalTime HORA_APERTURA = LocalTime.of(8, 0);
    private static final LocalTime HORA_CIERRE = LocalTime.of(20, 0);
    private static final Duration MARGEN = Duration.ofMinutes(30);

    private final RepositorioTurno repositorioTurno;
    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioConsultorio repositorioConsultorio;
    private final RepositorioTratamiento repositorioTratamiento;
    private final TurnoMapper turnoMapper;
    private final ServicioEmail servicioEmail;
    private final RepositorioCredencial repositorioCredencial;

    @Override
    @Transactional
    public TurnoRespuesta crear(TurnoNuevo nuevo) {
        validarFechaHora(nuevo.fechaHora());

        EntidadPaciente paciente = repositorioPaciente
                .findById(nuevo.id_paciente())
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Paciente",
                        "No se ha encontrado.",
                        nuevo.id_paciente(),
                        "No se ha encontrado a ningún paciente con aquel ID."
                ));

        // El tratamiento es opcional: un turno puede ser una consulta sin tratamiento.
        EntidadTratamiento tratamiento = null;
        if (nuevo.id_tratamiento() != null) {
            tratamiento = repositorioTratamiento
                    .findById(nuevo.id_tratamiento())
                    .orElseThrow(() -> new EntidadNoEncontradaException(
                            "Tratamiento",
                            "No se ha encontrado.",
                            nuevo.id_tratamiento(),
                            "No se ha encontrado ningún tratamiento con aquel ID."
                    ));
        }

        EntidadConsultorio consultorio = repositorioConsultorio
                .findById(nuevo.id_consultorio())
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Consultorio",
                        "No se ha encontrado.",
                        nuevo.id_consultorio(),
                        "No se ha encontrado ningún consultorio con aquel ID."
                ));

        EntidadUsuarios profesional = repositorioUsuario
                .findById(nuevo.id_profesional())
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Profesional",
                        "No se ha encontrado.",
                        nuevo.id_profesional(),
                        "No se ha encontrado a ningún profesional con aquel ID."
                ));

        validarDisponibilidad(profesional.getId(), consultorio.getId(), nuevo.fechaHora(), null);

        EntidadTurno entidadTurno = turnoMapper.toEntity(nuevo);
        entidadTurno.setConsultorio(consultorio);
        entidadTurno.setProfesional(profesional);
        entidadTurno.setTratamiento(tratamiento);
        entidadTurno.setPaciente(paciente);
        entidadTurno.setEstadoTurno(EstadoTurno.PENDIENTE);
        entidadTurno.setEstadoFacturacionDeTurno(EstadoFacturacionDeTurno.NO_FACTURADO);
        entidadTurno.setEliminado(false);

        EntidadTurno guardado = repositorioTurno.save(entidadTurno);
        return turnoMapper.toRespuestaDto(guardado);
    }

    private String conflictoDisponibilidad(Long idProfesional,
                                           Long idConsultorio,
                                           LocalDateTime fechaHora,
                                           Long idExcluido) {
        if (fechaHora == null) {
            return "Debe seleccionar una fecha y un horario.";
        }

        boolean profesionalOcupado = repositorioTurno.findByProfesionalId(idProfesional).stream()
                .filter(t -> !estaEliminado(t))
                .filter(t -> idExcluido == null || !idExcluido.equals(t.getId()))
                .filter(t -> t.getEstadoTurno() != EstadoTurno.CANCELADO)
                .anyMatch(t -> seSuperpone(t.getFechaHora(), fechaHora));

        if (profesionalOcupado) {
            return "El profesional ya tiene un turno dentro de los próximos 30 minutos.";
        }

        boolean consultorioOcupado = repositorioTurno.findByConsultorioId(idConsultorio).stream()
                .filter(t -> !estaEliminado(t))
                .filter(t -> idExcluido == null || !idExcluido.equals(t.getId()))
                .filter(t -> t.getEstadoTurno() != EstadoTurno.CANCELADO)
                .anyMatch(t -> seSuperpone(t.getFechaHora(), fechaHora));

        if (consultorioOcupado) {
            return "La sala ya está reservada dentro de los próximos 30 minutos.";
        }

        return null;
    }

    private void validarDisponibilidad(Long idProfesional,
                                       Long idConsultorio,
                                       LocalDateTime fechaHora,
                                       Long idExcluido) {
        String conflicto = conflictoDisponibilidad(idProfesional, idConsultorio, fechaHora, idExcluido);
        if (conflicto != null) {
            throw new RecursoOcupadoException(conflicto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DisponibilidadTurno verificarDisponibilidad(LocalDateTime fechaHora,
                                                       Long idProfesional,
                                                       Long idConsultorio,
                                                       Long idExcluido) {
        String conflicto = conflictoDisponibilidad(idProfesional, idConsultorio, fechaHora, idExcluido);
        return conflicto == null
                ? new DisponibilidadTurno(true, "")
                : new DisponibilidadTurno(false, conflicto);
    }

    private boolean seSuperpone(LocalDateTime existente, LocalDateTime nuevo) {
        if (existente == null || nuevo == null) return false;
        return Duration.between(existente, nuevo).abs().compareTo(MARGEN) < 0;
    }

    private boolean estaEliminado(EntidadTurno turno) {
        return Boolean.TRUE.equals(turno.getEliminado());
    }

    private EntidadTurno obtenerTurnoActivo(Long id) {
        EntidadTurno turno = repositorioTurno
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Turno",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún turno con aquel ID."
                ));

        if (estaEliminado(turno)) {
            throw new EntidadNoEncontradaException(
                    "Turno",
                    "No se ha encontrado.",
                    id,
                    "No se ha encontrado ningún turno con aquel ID."
            );
        }

        return turno;
    }

    private void validarFechaHora(LocalDateTime fechaHora) {
        if (fechaHora == null) {
            throw new ReglaNegocioException("La fecha y hora del turno son obligatorias.");
        }

        LocalDateTime ahora = LocalDateTime.now();

        if (fechaHora.isBefore(ahora)) {
            throw new ReglaNegocioException("No se puede registrar un turno en una fecha pasada.");
        }

        if (fechaHora.isAfter(ahora.plusMonths(2))) {
            throw new ReglaNegocioException("El turno no puede superar los 2 meses en el futuro.");
        }

        LocalTime hora = fechaHora.toLocalTime();
        if (hora.isBefore(HORA_APERTURA) || hora.isAfter(HORA_CIERRE)) {
            throw new ReglaNegocioException("El horario debe estar entre las 08:00 y las 20:00.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TurnoRespuesta buscarPorId(Long id) {
        return turnoMapper.toRespuestaDto(obtenerTurnoActivo(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TurnoRespuesta> buscarTodos() {
        return repositorioTurno
                .findAll()
                .stream()
                .filter(t -> !estaEliminado(t))
                .map(turnoMapper::toRespuestaDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TurnoRespuesta> buscarPorProfesional(String username) {
        EntidadCredencial credencial = repositorioCredencial
                .findByUsername(username)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Credencial",
                        "No se ha encontrado.",
                        null,
                        "No se ha encontrado el usuario autenticado."
                ));

        if (credencial.getUsuario() == null) {
            throw new EntidadNoEncontradaException(
                    "Usuario",
                    "No se ha encontrado.",
                    null,
                    "El usuario autenticado no tiene un usuario asociado."
            );
        }

        return repositorioTurno
                .findByProfesionalId(credencial.getUsuario().getId())
                .stream()
                .filter(t -> !estaEliminado(t))
                .map(turnoMapper::toRespuestaDto)
                .toList();
    }

    @Override
    @Transactional
    public TurnoRespuesta actualizar(Long id, TurnoActualizar turno) {
        EntidadTurno buscado = obtenerTurnoActivo(id);

        if (turno.fechaHora() != null && !turno.fechaHora().equals(buscado.getFechaHora())) {
            validarFechaHora(turno.fechaHora());
            validarDisponibilidad(
                    buscado.getProfesional().getId(),
                    buscado.getConsultorio().getId(),
                    turno.fechaHora(),
                    id
            );
            buscado.setFechaHora(turno.fechaHora());
        }

        buscado.setEstadoTurno(turno.estadoTurno());

        // Al confirmar un turno, queda marcado automáticamente como facturado.
        if (turno.estadoTurno() == EstadoTurno.CONFIRMADO) {
            buscado.setEstadoFacturacionDeTurno(EstadoFacturacionDeTurno.FACTURADO);
        }

        EntidadTurno actualizado = repositorioTurno.save(buscado);
        return turnoMapper.toRespuestaDto(actualizado);
    }

    @Override
    @Transactional
    public TurnoRespuesta cambiarEstado(Long id, EstadoTurno estado) {
        if (estado != EstadoTurno.CANCELADO && estado != EstadoTurno.FINALIZADO) {
            throw new ReglaNegocioException(
                    "Desde el panel profesional solo se puede cambiar el estado a CANCELADO o FINALIZADO."
            );
        }

        EntidadTurno buscado = obtenerTurnoActivo(id);
        buscado.setEstadoTurno(estado);
        return turnoMapper.toRespuestaDto(repositorioTurno.save(buscado));
    }

    @Transactional
    public void finalizarTurnosViejos() {
        List<EntidadTurno> turnos = repositorioTurno.findByFechaHoraBefore(LocalDateTime.now());

        for (EntidadTurno entidadTurno : turnos) {
            if (estaEliminado(entidadTurno)) continue;

            if (entidadTurno.getEstadoTurno().equals(EstadoTurno.PENDIENTE)
                    || entidadTurno.getEstadoTurno().equals(EstadoTurno.CONFIRMADO)) {
                entidadTurno.setEstadoTurno(EstadoTurno.FINALIZADO);
            }
        }
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        EntidadTurno buscado = obtenerTurnoActivo(id);

        // Baja lógica: el registro permanece en la BD para conservar las facturas
        // que lo referencian, pero deja de aparecer y de bloquear agenda.
        buscado.setEliminado(true);
        buscado.setEstadoTurno(EstadoTurno.CANCELADO);
        repositorioTurno.save(buscado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TurnoFacturable> obtenerTurnosFacturables(Long idPaciente) {
        return repositorioTurno.findByPacienteIdAndEstadoFacturacionDeTurnoAndEstadoTurno(idPaciente,
                        EstadoFacturacionDeTurno.NO_FACTURADO,
                        EstadoTurno.FINALIZADO)
                .stream()
                .filter(t -> !estaEliminado(t))
                .map(turnoMapper::toFacturableDTO)
                .toList();
    }

    public void enviarRecordatorio() {
        LocalDate manana = LocalDate.now().plusDays(1);
        LocalDateTime inicio = manana.atStartOfDay();
        LocalDateTime fin = manana.atTime(23, 59, 59);

        List<EntidadTurno> turnos =
                repositorioTurno.findByFechaHoraBetweenAndEstadoTurno(inicio, fin, EstadoTurno.PENDIENTE);

        for (EntidadTurno entidadTurno : turnos) {
            if (estaEliminado(entidadTurno)) continue;

            servicioEmail.enviarRecordatorioTurno(
                    entidadTurno.getPaciente(),
                    entidadTurno
            );
        }
    }
}
