package com.GrupoProga3.Gestor_Salud.service;

import com.GrupoProga3.Gestor_Salud.entity.Tratamientos.Tratamientos;
import com.GrupoProga3.Gestor_Salud.entity.Tratamientos.TratamientosDTO;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.TratamientosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TratamientosService {

    private final TratamientosRepository tratamientosRepository;

    public TratamientosService(TratamientosRepository tratamientosRepository) {
        this.tratamientosRepository = tratamientosRepository;
    }

    @Transactional(readOnly = true)
    public List<TratamientosDTO> getAll() {
        return tratamientosRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public TratamientosDTO getById(Long id) {
        return tratamientosRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Tratamiento no encontrado con id: " + id));
    }

    public TratamientosDTO create(TratamientosDTO dto) {
        Tratamientos tratamiento = new Tratamientos();
        tratamiento.setNombre(dto.getNombre());
        tratamiento.setDescripcion(dto.getDescripcion());
        return toDTO(tratamientosRepository.save(tratamiento));
    }

    public TratamientosDTO update(Long id, TratamientosDTO dto) {
        Tratamientos tratamiento = tratamientosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tratamiento no encontrado con id: " + id));
        tratamiento.setNombre(dto.getNombre());
        tratamiento.setDescripcion(dto.getDescripcion());
        return toDTO(tratamientosRepository.save(tratamiento));
    }

    public void delete(Long id) {
        if (!tratamientosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tratamiento no encontrado con id: " + id);
        }
        tratamientosRepository.deleteById(id);
    }

    private TratamientosDTO toDTO(Tratamientos tratamiento) {
        TratamientosDTO dto = new TratamientosDTO();
        dto.setIdTratamiento(tratamiento.getIdTratamiento());
        dto.setNombre(tratamiento.getNombre());
        dto.setDescripcion(tratamiento.getDescripcion());
        return dto;
    }
}
