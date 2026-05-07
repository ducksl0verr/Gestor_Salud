package com.GrupoProga3.Gestor_Salud.service;

import com.GrupoProga3.Gestor_Salud.entity.TipoGasto.TipoGasto;
import com.GrupoProga3.Gestor_Salud.entity.TipoGasto.TipoGastoDTO;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.TipoGastoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TipoGastoService {

    private final TipoGastoRepository tipoGastoRepository;

    public TipoGastoService(TipoGastoRepository tipoGastoRepository) {
        this.tipoGastoRepository = tipoGastoRepository;
    }

    @Transactional(readOnly = true)
    public List<TipoGastoDTO> getAll() {
        return tipoGastoRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public TipoGastoDTO getById(Long id) {
        return tipoGastoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de gasto no encontrado con id: " + id));
    }

    public TipoGastoDTO create(TipoGastoDTO dto) {
        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setNombre(dto.getNombre());
        return toDTO(tipoGastoRepository.save(tipoGasto));
    }

    public TipoGastoDTO update(Long id, TipoGastoDTO dto) {
        TipoGasto tipoGasto = tipoGastoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de gasto no encontrado con id: " + id));
        tipoGasto.setNombre(dto.getNombre());
        return toDTO(tipoGastoRepository.save(tipoGasto));
    }

    public void delete(Long id) {
        if (!tipoGastoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de gasto no encontrado con id: " + id);
        }
        tipoGastoRepository.deleteById(id);
    }

    private TipoGastoDTO toDTO(TipoGasto tipoGasto) {
        TipoGastoDTO dto = new TipoGastoDTO();
        dto.setIdTipoGasto(tipoGasto.getIdTipoGasto());
        dto.setNombre(tipoGasto.getNombre());
        return dto;
    }
}
