package com.GrupoProga3.Gestor_Salud.Turno;

import com.GrupoProga3.Gestor_Salud.Pacientes.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pacientes.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.Salas.EntidadSala;
import com.GrupoProga3.Gestor_Salud.Salas.RepositorioSala;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.Tratamientos.RepositorioTratamiento;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoActualizar;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoNuevo;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs.TurnoRespuesta;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.Mapper.TurnoMapper;
import com.GrupoProga3.Gestor_Salud.Usuarios.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.Usuarios.UsuarioRepositorio;
import com.GrupoProga3.Gestor_Salud.common.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioTurno implements IServicioTurno {
    private final RepositorioTurno repositorioTurno;
    private final RepositorioPaciente repositorioPaciente;
    private final UsuarioRepositorio repositorioUsuario;
    private final RepositorioSala repositorioSala;
    private final RepositorioTratamiento repositorioTratamiento;
    private final TurnoMapper turnoMapper;

    @Override
    public TurnoRespuesta crear(TurnoNuevo nuevo) {
        System.out.println(nuevo);
        EntidadTurno entidadTurno = turnoMapper.toEntity(nuevo);

        EntidadPaciente paciente = repositorioPaciente
                .findById(nuevo.id_paciente())
                .orElseThrow(() -> new PacienteNoEncontradoException("Paciente no encontrado"));
        entidadTurno.setId_paciente(paciente);

        EntidadTratamiento tratamiento = repositorioTratamiento
                .findById(nuevo.id_tratamiento())
                .orElseThrow(()-> new TratamientoNoEncontradoException("El tratamiento no existe."));
        entidadTurno.setId_tratamiento(tratamiento);

        EntidadSala sala = repositorioSala
                .findById(nuevo.id_sala())
                /// Agrega una verificación para poder ver si la sala ya está registrada para esa hora y fecha.
                .orElseThrow(()-> new SalaNoEncontradaException("No se encontró la sala."));
        entidadTurno.setId_sala(sala);

        EntidadUsuarios profesional = repositorioUsuario
                .findById(nuevo.id_profesional())
                /// Agregar método para asegurarse de que el profrsional no tiende otro turno a esa hora y fecha
                .orElseThrow(()-> new UsuarioNoEncontradoException("No se encontró al profesional."));
        entidadTurno.setId_profesional(profesional);

        EntidadTurno guardado = repositorioTurno.save(entidadTurno);
        return turnoMapper.toDto(guardado);
    }

    @Override
    public TurnoRespuesta buscarPorId(Long id) {
        EntidadTurno turno = repositorioTurno
                .findById(id)
                .orElseThrow(()-> new TurnoNoEncontradoException("No se encontró ningún turno con ese id."));

        return turnoMapper.toDto(turno);
    }

    @Override
    public List<TurnoRespuesta> buscarTodos() {
        return repositorioTurno
                .findAll()
                .stream()
                .map(turnoMapper::toDto)
                .toList();
    }

    @Override
    public TurnoRespuesta actualizar(Long id, TurnoActualizar turno) {
        EntidadTurno buscado = repositorioTurno
                .findById(id)
                .orElseThrow(()-> new TurnoNoEncontradoException("No se encontró ningún turno con ese id."));

        buscado.setHora(turno.hora());
        buscado.setFecha(turno.fecha());
        buscado.setEstado(turno.estado());

        EntidadTurno actualizado = repositorioTurno.save(buscado);
        return turnoMapper.toDto(actualizado);
    }

    @Override
    public void borrar(Long id) {
        EntidadTurno buscado = repositorioTurno
                .findById(id)
                .orElseThrow(()-> new TurnoNoEncontradoException("No se encontró ningún turno con ese id."));

        repositorioTurno.delete(buscado);
    }
}
