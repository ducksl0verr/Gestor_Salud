package com.GrupoProga3.Gestor_Salud.service;

import com.GrupoProga3.Gestor_Salud.entity.MetodosDePagos.MetodosDePagos;
import com.GrupoProga3.Gestor_Salud.entity.MetodosDePagos.MetodosDePagosDTO;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.MetodosDePagosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MetodosDePagosService {

    private final MetodosDePagosRepository metodosDePagosRepository;

    public MetodosDePagosService(MetodosDePagosRepository metodosDePagosRepository) {
        this.metodosDePagosRepository = metodosDePagosRepository;
    }

    @Transactional(readOnly = true)
    public List<MetodosDePagosDTO> getAll() {
        return metodosDePagosRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public MetodosDePagosDTO getById(Long id) {
        return metodosDePagosRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Método de pago no encontrado con id: " + id));
    }

    public MetodosDePagosDTO create(MetodosDePagosDTO dto) {
        MetodosDePagos metodo = new MetodosDePagos();
        metodo.setNombre(dto.getNombre());
        return toDTO(metodosDePagosRepository.save(metodo));
    }

    public MetodosDePagosDTO update(Long id, MetodosDePagosDTO dto) {
        MetodosDePagos metodo = metodosDePagosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Método de pago no encontrado con id: " + id));
        metodo.setNombre(dto.getNombre());
        return toDTO(metodosDePagosRepository.save(metodo));
    }

    public void delete(Long id) {
        if (!metodosDePagosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Método de pago no encontrado con id: " + id);
        }
        metodosDePagosRepository.deleteById(id);
    }

    private MetodosDePagosDTO toDTO(MetodosDePagos metodo) {
        MetodosDePagosDTO dto = new MetodosDePagosDTO();
        dto.setIdMetodoDePago(metodo.getIdMetodoDePago());
        dto.setNombre(metodo.getNombre());
        return dto;
    }
}
