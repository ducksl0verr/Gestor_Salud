package com.GrupoProga3.Gestor_Salud.service;

import com.GrupoProga3.Gestor_Salud.entity.Especialidades.Especialidades;
import com.GrupoProga3.Gestor_Salud.entity.Especialidades.EspecialidadesDTO;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.EspecialidadesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EspecialidadesService {

    private final EspecialidadesRepository especialidadesRepository;

    public EspecialidadesService(EspecialidadesRepository especialidadesRepository) {
        this.especialidadesRepository = especialidadesRepository;
    }

    @Transactional(readOnly = true)
    public List<EspecialidadesDTO> getAll() {
        return especialidadesRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public EspecialidadesDTO getById(Long id) {
        return especialidadesRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con id: " + id));
    }

    public EspecialidadesDTO create(EspecialidadesDTO dto) {
        Especialidades especialidad = new Especialidades();
        especialidad.setNombre(dto.getNombre());
        return toDTO(especialidadesRepository.save(especialidad));
    }

    public EspecialidadesDTO update(Long id, EspecialidadesDTO dto) {
        Especialidades especialidad = especialidadesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con id: " + id));
        especialidad.setNombre(dto.getNombre());
        return toDTO(especialidadesRepository.save(especialidad));
    }

    public void delete(Long id) {
        if (!especialidadesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Especialidad no encontrada con id: " + id);
        }
        especialidadesRepository.deleteById(id);
    }

    private EspecialidadesDTO toDTO(Especialidades especialidad) {
        EspecialidadesDTO dto = new EspecialidadesDTO();
        dto.setIdEspecialidad(especialidad.getIdEspecialidad());
        dto.setNombre(especialidad.getNombre());
        return dto;
    }
}
