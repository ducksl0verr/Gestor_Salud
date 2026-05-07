package com.GrupoProga3.Gestor_Salud.service;

import com.GrupoProga3.Gestor_Salud.entity.Salas.Salas;
import com.GrupoProga3.Gestor_Salud.entity.Salas.SalasDTO;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.SalasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalasService {

    private final SalasRepository salasRepository;

    public SalasService(SalasRepository salasRepository) {
        this.salasRepository = salasRepository;
    }

    @Transactional(readOnly = true)
    public List<SalasDTO> getAll() {
        return salasRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public SalasDTO getById(Long id) {
        return salasRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Sala no encontrada con id: " + id));
    }

    public SalasDTO create(SalasDTO dto) {
        Salas sala = new Salas();
        sala.setNombre(dto.getNombre());
        return toDTO(salasRepository.save(sala));
    }

    public SalasDTO update(Long id, SalasDTO dto) {
        Salas sala = salasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala no encontrada con id: " + id));
        sala.setNombre(dto.getNombre());
        return toDTO(salasRepository.save(sala));
    }

    public void delete(Long id) {
        if (!salasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sala no encontrada con id: " + id);
        }
        salasRepository.deleteById(id);
    }

    private SalasDTO toDTO(Salas sala) {
        SalasDTO dto = new SalasDTO();
        dto.setIdSalas(sala.getIdSalas());
        dto.setNombre(sala.getNombre());
        return dto;
    }
}
