package com.GrupoProga3.Gestor_Salud.features.Tratamientos;

import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.DTOs.TratamientoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.DTOs.TratamientoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.MAPPER.TratamientoMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ServicioTratamiento implements IServicioTratamiento{
    private final RepositorioTratamiento repositorioTratamiento;
    private final TratamientoMapper tratamientoMapper;

    @Override
    @Transactional
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
                .orElseThrow(() -> new EntidadNoEncontradaException("Tratamiento",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún tratamiento con aquel ID."));
        return tratamientoMapper.toDTO(buscado);
    }

    @Override
    @Transactional
    public TratamientoRespuesta actualizar(Long id, TratamientoNuevo dto) {
        EntidadTratamiento buscado = repositorioTratamiento.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Tratamiento",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún tratamiento con aquel ID."));
        buscado.setDescripcion(dto.descripcion());
        buscado.setNombre(dto.nombre());
        buscado.setPrecio(dto.precio());
        EntidadTratamiento actualizaddo =  repositorioTratamiento.save(buscado);
        System.out.println(actualizaddo);
        return tratamientoMapper.toDTO(actualizaddo);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        EntidadTratamiento buscado = repositorioTratamiento.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Tratamiento",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún tratamiento con aquel ID."));

        repositorioTratamiento.delete(buscado);
    }
}
