package com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones;

import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs.SalaInternacionActualizar;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs.SalaInternacionNueva;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs.SalaInternacionRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.EntidadSalaInternacion;
import com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.MAPPER.SalaInternacionMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.ReglaNegocioException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioSalaInternacion implements IServicioSalaInternacion {
    private final RepositorioSalaInternacion repositorioSalaInternacion;
    private final RepositorioPaciente repositorioPaciente;
    private final SalaInternacionMapper salaInternacionMapper;


    public void validacionCapacidad (EntidadSalaInternacion sala){
        int internados = sala.getPacientes() == null ? 0 : sala.getPacientes().size();

        if(internados >= sala.getCapacidad_maxima()){
            throw new RecursoOcupadoException("La sala ya está llena");
        }
    }

    public void validacionPaciente (EntidadPaciente paciente){
        if(paciente.getSalaInternacion() != null){
            throw new RecursoOcupadoException(
                    "El paciente ya se encuentra internado en la sala "
                            + paciente.getSalaInternacion().getId() + "."
            );
        }
    }

    @Transactional
    public SalaInternacionRespuesta internarPaciente (Long idSala, Long idPaciente){
        EntidadSalaInternacion sala = repositorioSalaInternacion
                .findById(idSala)
                .orElseThrow(()-> new EntidadNoEncontradaException("Sala de Internación",
                        "No se ha encontrado.",
                        idSala,
                        "No se ha encontrado ninguna sala de internación con aquel ID."));

        EntidadPaciente paciente = repositorioPaciente
                .findById(idPaciente)
                .orElseThrow(()-> new EntidadNoEncontradaException("Paciente",
                        "No se ha encontrado.",
                        idPaciente,
                        "No se ha encontrado ningún paciente con aquel ID."));

        validacionPaciente(paciente);
        validacionCapacidad(sala);

        if (sala.getPacientes() == null) {
            sala.setPacientes(new ArrayList<>());
        }

        sala.getPacientes().add(paciente);
        paciente.setSalaInternacion(sala);

        EntidadSalaInternacion salaactualizada = repositorioSalaInternacion.save(sala);

        return salaInternacionMapper.toDTO(salaactualizada);
    }

    @Override
    @Transactional
    public SalaInternacionRespuesta egresarPaciente(Long idSala, Long idPaciente) {
        EntidadSalaInternacion sala = repositorioSalaInternacion
                .findById(idSala)
                .orElseThrow(() -> new EntidadNoEncontradaException("Sala de Internación",
                        "No se ha encontrado.",
                        idSala,
                        "No se ha encontrado ninguna sala de internación con aquel ID."));

        EntidadPaciente paciente = repositorioPaciente
                .findById(idPaciente)
                .orElseThrow(() -> new EntidadNoEncontradaException("Paciente",
                        "No se ha encontrado.",
                        idPaciente,
                        "No se ha encontrado ningún paciente con aquel ID."));

        if (paciente.getSalaInternacion() == null
                || !paciente.getSalaInternacion().getId().equals(idSala)) {
            throw new ReglaNegocioException("El paciente no está internado en esta sala.");
        }

        sala.getPacientes().remove(paciente);
        paciente.setSalaInternacion(null);

        repositorioPaciente.save(paciente);

        return salaInternacionMapper.toDTO(sala);
    }

    @Override
    @Transactional
    public SalaInternacionRespuesta crear(SalaInternacionNueva salaInternacionNueva) {
        EntidadSalaInternacion sala = salaInternacionMapper.toEntity(salaInternacionNueva);
        sala.setPacientes(new ArrayList<>());

        EntidadSalaInternacion creada = repositorioSalaInternacion.save(sala);

        return salaInternacionMapper.toDTO(creada);
    }

    @Override
    @Transactional
    public SalaInternacionRespuesta actualizar(Long id, SalaInternacionActualizar salaInternacionActualizar) {
        EntidadSalaInternacion buscada = repositorioSalaInternacion
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("Sala de Internación",
                                "No se ha encontrado.",
                                id,
                                "No se ha encontrado ninguna sala de internación con aquel ID."));

        int internados = buscada.getPacientes() == null ? 0 : buscada.getPacientes().size();

        if (salaInternacionActualizar.capacidad_maxima() < internados) {
            throw new ReglaNegocioException(
                    "La capacidad no puede ser menor a la cantidad de pacientes internados ("
                            + internados + ").");
        }

        buscada.setCapacidad_maxima(salaInternacionActualizar.capacidad_maxima());

        EntidadSalaInternacion actualizada = repositorioSalaInternacion.save(buscada);

        return salaInternacionMapper.toDTO(actualizada);
    }

    @Override
    public SalaInternacionRespuesta buscarPorId(Long id) {
        EntidadSalaInternacion buscada = repositorioSalaInternacion
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("Sala de Internación",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ninguna sala de internación con aquel ID."));
        return salaInternacionMapper.toDTO(buscada);
    }

    @Override
    public List<SalaInternacionRespuesta> buscarTodos() {
        return repositorioSalaInternacion
                .findAll()
                .stream()
                .map(salaInternacionMapper::toDTO)
                .toList();
    }
}
