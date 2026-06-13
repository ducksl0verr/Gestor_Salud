package com.GrupoProga3.Gestor_Salud.Salas_Internaciones;

import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionActualizar;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionNueva;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionRespuesta;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.EntidadSalaInternacion;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.MAPPER.SalaInternacionMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioSalaInternacion implements IServicioSalaInternacion {
    private final RepositorioSalaInternacion repositorioSalaInternacion;
    private final RepositorioPaciente repositorioPaciente;
    private final SalaInternacionMapper salaInternacionMapper;


    public void validacionCapacidad (EntidadSalaInternacion sala){
        if(sala.getPacientes().size()>= sala.getCapacidad_maxima()){
            throw new RecursoOcupadoException("La sala ya está llena");
        }
    }

    public void validacionPaciente (EntidadPaciente paciente){
        List<EntidadSalaInternacion> salas = repositorioSalaInternacion.findAll();
        for(EntidadSalaInternacion sala : salas){
            if(sala.getPacientes().contains(paciente)){
                throw new RecursoOcupadoException(
                        "El paciente ya se encuentra internado."
                );
            }
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
        sala.getPacientes().add(paciente);
        paciente.setSalaInternacion(sala);

        EntidadSalaInternacion salaactualizada = repositorioSalaInternacion.save(sala);

        return salaInternacionMapper.toDTO(salaactualizada);
    }

    @Override
    @Transactional
    public SalaInternacionRespuesta crear(SalaInternacionNueva salaInternacionNueva) {
        System.out.println(salaInternacionNueva);
        EntidadSalaInternacion sala = repositorioSalaInternacion.save(salaInternacionMapper.toEntity(salaInternacionNueva));
        System.out.println(sala);
        return salaInternacionMapper.toDTO(sala);
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

        buscada.setCapacidad_maxima(buscada.getCapacidad_maxima());

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
