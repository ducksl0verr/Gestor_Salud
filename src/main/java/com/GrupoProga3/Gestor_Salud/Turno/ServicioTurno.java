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
import com.GrupoProga3.Gestor_Salud.common.excepciones.Consultorios.ConsultorioNoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public TurnoRespuesta crear(TurnoNuevo nuevo) {
        System.out.println(nuevo);
        EntidadTurno entidadTurno = turnoMapper.toEntity(nuevo);

        EntidadPaciente paciente = repositorioPaciente
                .findById(nuevo.id_paciente())
                .orElseThrow(() -> new PacienteNoEncontradoException("Paciente no encontrado"));
        entidadTurno.setPaciente(paciente);

        EntidadTratamiento tratamiento = repositorioTratamiento
                .findById(nuevo.id_tratamiento())
                .orElseThrow(()-> new TratamientoNoEncontradoException("El tratamiento no existe."));
        entidadTurno.setTratamiento(tratamiento);

        EntidadConsultorio consultorio = repositorioConsultorio
                .findById(nuevo.id_consultorio())
                /// Agrega una verificación para poder ver si la sala ya está registrada para esa hora y fecha.
                .orElseThrow(()-> new ConsultorioNoEncontradoException("No se encontró el consultorio."));
        entidadTurno.setConsultorio(consultorio);

        EntidadUsuarios profesional = repositorioUsuario
                .findById(nuevo.id_profesional())
                /// Agregar método para asegurarse de que el profrsional no tiende otro turno a esa hora y fecha
                .orElseThrow(()-> new UsuarioNoEncontradoException("No se encontró al profesional."));
        entidadTurno.setProfesional(profesional);

        EntidadTurno guardado = repositorioTurno.save(entidadTurno);
        return turnoMapper.toRespuestaDto(guardado);
    }

    @Override
    public TurnoRespuesta buscarPorId(Long id) {
        EntidadTurno turno = repositorioTurno
                .findById(id)
                .orElseThrow(()-> new TurnoNoEncontradoException("No se encontró ningún turno con ese id."));

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
    public TurnoRespuesta actualizar(Long id, TurnoActualizar turno) {
        EntidadTurno buscado = repositorioTurno
                .findById(id)
                .orElseThrow(()-> new TurnoNoEncontradoException("No se encontró ningún turno con ese id."));

        buscado.setHora(turno.hora());
        buscado.setFecha(turno.fecha());
        buscado.setEstadoTurno(turno.estadoTurno());

        EntidadTurno actualizado = repositorioTurno.save(buscado);
        return turnoMapper.toRespuestaDto(actualizado);
    }

    @Override
    public void borrar(Long id) {
        EntidadTurno buscado = repositorioTurno
                .findById(id)
                .orElseThrow(()-> new TurnoNoEncontradoException("No se encontró ningún turno con ese id."));

        repositorioTurno.delete(buscado);
    }

    @Override
    public List<TurnoFacturable> obtenerTurnosFacturables(Long idPaciente) {
        return repositorioTurno.findByPacienteIdAndEstadoFacturacionDeTurnoAndEstadoTurno(idPaciente,
                        EstadoFacturacionDeTurno.NO_FACTURADO,
                        EstadoTurno.REALIZADO)
                .stream()
                .map(turnoMapper::toFacturableDTO)
                .toList();
    }

    public void enviarRecordatorio (){
        LocalDate manana = LocalDate.now().plusDays(1);

        List<EntidadTurno> turnos =
                repositorioTurno.findByFechaAndEstadoTurno(manana, EstadoTurno.NO_REALIZADO);

        for (EntidadTurno entidadTurno : turnos) {
            servicioEmail.enviarRecordatorioTurno(
                    entidadTurno.getPaciente(),
                    entidadTurno
            );
        }
    }

}
