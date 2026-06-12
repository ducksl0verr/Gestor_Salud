package com.GrupoProga3.Gestor_Salud.Tratamientos;

import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.DTOs.TratamientoNuevo;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.DTOs.TratamientoRespuesta;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.MAPPER.TratamientoMapper;
import com.GrupoProga3.Gestor_Salud.common.TratamientoNoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ServicioTratamiento implements IServicioTratamiento{
    private final RepositorioTratamiento repositorioTratamiento;
    private final TratamientoMapper tratamientoMapper;
    @Override
    public TratamientoRespuesta crear(TratamientoNuevo tratamientoNuevo) {

        System.out.println(tratamientoNuevo);
        EntidadTratamiento tratamiento = tratamientoMapper.toEntity(tratamientoNuevo);
        EntidadTratamiento guardado = repositorioTratamiento.save(tratamiento);
        System.out.println("Tratamiento guardado = " + guardado);
        return tratamientoMapper.toDTO(guardado);
    }

    @Override
    public List<TratamientoRespuesta> buscarTodos() {
        return repositorioTratamiento.findAll()
                .stream()
                .map(tratamientoMapper::toDTO)
                .toList();
    }

    @Override
    public TratamientoRespuesta buscarPorId(Long id) {
        EntidadTratamiento buscado = repositorioTratamiento.findById(id)
                .orElseThrow(() -> new TratamientoNoEncontradoException("Tratamiento no encontrado"));
        return tratamientoMapper.toDTO(buscado);
    }

    @Override
    public void borrar(Long id) {
        EntidadTratamiento buscado = repositorioTratamiento.findById(id)
                .orElseThrow(() -> new TratamientoNoEncontradoException("Tratamiento no encontrado"));
        repositorioTratamiento.delete(buscado);
    }
}
