package com.GrupoProga3.Gestor_Salud.Salas_Internaciones;

import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionActualizar;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionNueva;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs.SalaInternacionRespuesta;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.EntidadSalaInternacion;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.MAPPER.SalaInternacionMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.SalaInternacionNoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioSalaInternacion implements IServicioSalaInternacion {
    private final RepositorioSalaInternacion repositorioSalaInternacion;
    private final SalaInternacionMapper salaInternacionMapper;

    @Override
    public SalaInternacionRespuesta crear(SalaInternacionNueva salaInternacionNueva) {
        System.out.println(salaInternacionNueva);
        EntidadSalaInternacion sala = repositorioSalaInternacion.save(salaInternacionMapper.toEntity(salaInternacionNueva));
        System.out.println(sala);
        return salaInternacionMapper.toDTO(sala);
    }

    @Override
    public SalaInternacionRespuesta actualizar(Long id, SalaInternacionActualizar salaInternacionActualizar) {
        EntidadSalaInternacion buscada = repositorioSalaInternacion
                .findById(id)
                .orElseThrow(()-> new SalaInternacionNoEncontradaException("La sala de internacion que busca no fue encontrada."));

        buscada.setCapacidad_maxima(buscada.getCapacidad_maxima());

        EntidadSalaInternacion actualizada = repositorioSalaInternacion.save(buscada);

        return salaInternacionMapper.toDTO(actualizada);
    }

    @Override
    public SalaInternacionRespuesta buscarPorId(Long id) {
        EntidadSalaInternacion buscada = repositorioSalaInternacion
                .findById(id)
                .orElseThrow(()-> new SalaInternacionNoEncontradaException("La sala de internacion que busca no fue encontrada."));
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
