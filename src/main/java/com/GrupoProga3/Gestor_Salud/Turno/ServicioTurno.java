package com.GrupoProga3.Gestor_Salud.Turno;

import com.GrupoProga3.Gestor_Salud.Consultorios.RepositorioConsultorio;
import com.GrupoProga3.Gestor_Salud.Notificaciones.ServicioEmail;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.EntidadConsultorio;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.Tratamientos.RepositorioTratamiento;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoFacturable;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoRespuesta;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.Mapper.TurnoMapper;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.Usuarios.Repositorio.RepositorioUsuario;
import com.GrupoProga3.Gestor_Salud.common.*;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioTurno implements IServicioTurno {

    private final RepositorioTurno repositorioTurno;
    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioConsultorio repositorioConsultorio;
    private final RepositorioTratamiento repositorioTratamiento;
    private final TurnoMapper turnoMapper;
    private final ServicioEmail servicioEmail;

    @Override
    @Transactional
    public TurnoRespuesta crear(TurnoNuevo nuevo) {
        System.out.println(nuevo);
        EntidadTurno entidadTurno = turnoMapper.toEntity(nuevo);

        /// VALIDACIPON DE EXISTENCIA
        EntidadPaciente paciente = repositorioPaciente
                .findById(nuevo.id_paciente())
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Paciente",
                        "No se ha encontrado.",
                        nuevo.id_paciente(),
                        "No se ha encontrado a ningún paciente con aquel ID."
                ));

        EntidadTratamiento tratamiento = repositorioTratamiento
                .findById(nuevo.id_tratamiento())
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Tratamiento",
                        "No se ha encontrado.",
                        nuevo.id_tratamiento(),
                        "No se ha encontrado ningún tratamiento con aquel ID."
                ));

        EntidadConsultorio consultorio = repositorioConsultorio
                .findById(nuevo.id_consultorio())
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Consultorio",
                        "No se ha encontrado.",
                        nuevo.id_consultorio(),
                        "No se ha encontrado ningún consultorio con aquel ID."
                ));

        EntidadUsuarios profesional = repositorioUsuario
                .findById(nuevo.id_profesional())
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Profesional",
                        "No se ha encontrado.",
                        nuevo.id_profesional(),
                        "No se ha encontrado a ningún profesional con aquel ID."
                ));

                /// VALIDACIPON DE DISPONIBILIDAD
        validarDiponibilidad(profesional.getId(), consultorio.getId(), nuevo.fechaHora());

        entidadTurno.setConsultorio(consultorio);
        entidadTurno.setProfesional(profesional);
        entidadTurno.setTratamiento(tratamiento);
        entidadTurno.setPaciente(paciente);


        entidadTurno.setEstadoTurno(EstadoTurno.PENDIENTE);
        entidadTurno.setEstadoFacturacionDeTurno(EstadoFacturacionDeTurno.NO_FACTURADO);

        EntidadTurno guardado = repositorioTurno.save(entidadTurno);
        return turnoMapper.toRespuestaDto(guardado);
    }

    private void validarDiponibilidad (Long idProfesional, Long idConsultorio, LocalDateTime fechaHora) {
        if(repositorioTurno.existsByProfesionalIdAndFechaHora(idProfesional, fechaHora)) {
            throw new RecursoOcupadoException("El profesional ya tiene un turno a esa hora.");
        }
        if (repositorioTurno.existsByConsultorioIdAndFechaHora(idConsultorio, fechaHora)) {
            throw new RecursoOcupadoException("El consultorio ya está reservado a esa hora.");
        }
    }

    @Override
    public TurnoRespuesta buscarPorId(Long id) {
        EntidadTurno turno = repositorioTurno
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Turno",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún turno con aquel ID."
                ));

        return turnoMapper.toRespuestaDto(turno);
    }

    @Override
    public List<TurnoRespuesta> buscarTodos() {
        return repositorioTurno
                .findAll()
                .stream()
                .map(turnoMapper::toRespuestaDto)
                .toList();
    }

    @Override
    @Transactional
    public TurnoRespuesta actualizar(Long id, TurnoActualizar turno) {
        EntidadTurno buscado = repositorioTurno
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Turno",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún turno con aquel ID."
                ));
        buscado.setFechaHora(turno.fechaHora());
        buscado.setEstadoTurno(turno.estadoTurno());

        EntidadTurno actualizado = repositorioTurno.save(buscado);
        return turnoMapper.toRespuestaDto(actualizado);
    }

    @Transactional
    public void finalizarTurnosViejos(){
        List<EntidadTurno> turnos=repositorioTurno.findByFechaHoraBefore(LocalDateTime.now());

        for (EntidadTurno entidadTurno : turnos) {

            if(entidadTurno.getEstadoTurno().equals(EstadoTurno.PENDIENTE)
            ||
            entidadTurno.getEstadoTurno().equals(EstadoTurno.CONFIRMADO)){
                entidadTurno.setEstadoTurno(EstadoTurno.FINALIZADO);
            }
        }
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        EntidadTurno buscado = repositorioTurno
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Turno",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún turno con aquel ID."
                ));

        repositorioTurno.delete(buscado);
    }

    @Override
    public List<TurnoFacturable> obtenerTurnosFacturables(Long idPaciente) {
        return repositorioTurno.findByPacienteIdAndEstadoFacturacionDeTurnoAndEstadoTurno(idPaciente,
                        EstadoFacturacionDeTurno.NO_FACTURADO,
                        EstadoTurno.FINALIZADO)
                .stream()
                .map(turnoMapper::toFacturableDTO)
                .toList();
    }

    public void enviarRecordatorio (){
        LocalDate manana = LocalDate.now().plusDays(1);
        LocalDateTime inicio = manana.atStartOfDay();
        LocalDateTime fin = manana.atTime(23, 59, 59);

        List<EntidadTurno> turnos =
                repositorioTurno.findByFechaHoraBetweenAndEstadoTurno(inicio, fin, EstadoTurno.PENDIENTE);

        for (EntidadTurno entidadTurno : turnos) {
            servicioEmail.enviarRecordatorioTurno(
                    entidadTurno.getPaciente(),
                    entidadTurno
            );
        }
    }

}
