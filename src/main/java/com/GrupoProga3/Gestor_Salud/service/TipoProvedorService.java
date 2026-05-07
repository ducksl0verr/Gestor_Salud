package com.GrupoProga3.Gestor_Salud.service;

import com.GrupoProga3.Gestor_Salud.entity.TipoProvedor.TipoProvedor;
import com.GrupoProga3.Gestor_Salud.entity.TipoProvedor.TipoProvedorDTO;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.TipoProvedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TipoProvedorService {

    private final TipoProvedorRepository tipoProvedorRepository;

    public TipoProvedorService(TipoProvedorRepository tipoProvedorRepository) {
        this.tipoProvedorRepository = tipoProvedorRepository;
    }

    @Transactional(readOnly = true)
    public List<TipoProvedorDTO> getAll() {
        return tipoProvedorRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public TipoProvedorDTO getById(Long id) {
        return tipoProvedorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de proveedor no encontrado con id: " + id));
    }

    public TipoProvedorDTO create(TipoProvedorDTO dto) {
        TipoProvedor tipoProvedor = new TipoProvedor();
        tipoProvedor.setNombre(dto.getNombre());
        return toDTO(tipoProvedorRepository.save(tipoProvedor));
    }

    public TipoProvedorDTO update(Long id, TipoProvedorDTO dto) {
        TipoProvedor tipoProvedor = tipoProvedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de proveedor no encontrado con id: " + id));
        tipoProvedor.setNombre(dto.getNombre());
        return toDTO(tipoProvedorRepository.save(tipoProvedor));
    }

    public void delete(Long id) {
        if (!tipoProvedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de proveedor no encontrado con id: " + id);
        }
        tipoProvedorRepository.deleteById(id);
    }

    private TipoProvedorDTO toDTO(TipoProvedor tipoProvedor) {
        TipoProvedorDTO dto = new TipoProvedorDTO();
        dto.setIdTipoProvedor(tipoProvedor.getIdTipoProvedor());
        dto.setNombre(tipoProvedor.getNombre());
        return dto;
    }
}
