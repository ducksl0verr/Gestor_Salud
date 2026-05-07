package com.GrupoProga3.Gestor_Salud.entity.Diagnosticos;

import com.GrupoProga3.Gestor_Salud.entity.Diagnosticos.domain.DiagnosticosDTO;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.DiagnosticosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiagnosticosService {

    private final DiagnosticosRepository diagnosticosRepository;
    @Autowired
    private Diagnosticomapper diagnosticoMapper;

    public DiagnosticosService(DiagnosticosRepository diagnosticosRepository) {
        this.diagnosticosRepository = diagnosticosRepository;
    }


    public List<DiagnosticosDTO> getAll() {
        return diagnosticosRepository.findAll().stream().map(diagnosticoMapper::convertToDto).toList();
    }


    public DiagnosticosDTO getById(Long id) {
        return diagnosticosRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnóstico no encontrado con id: " + id));
    }

    public DiagnosticosDTO create(DiagnosticosDTO dto) {
        Diagnosticos diagnostico = new Diagnosticos();
        diagnostico.setNombre(dto.getNombre());
        diagnostico.setDescripcion(dto.getDescripcion());
        return toDTO(diagnosticosRepository.save(diagnostico));
    }

    public DiagnosticosDTO update(Long id, DiagnosticosDTO dto) {
        Diagnosticos diagnostico = diagnosticosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnóstico no encontrado con id: " + id));
        diagnostico.setNombre(dto.getNombre());
        diagnostico.setDescripcion(dto.getDescripcion());
        return toDTO(diagnosticosRepository.save(diagnostico));
    }

    public void delete(Long id) {
        if (!diagnosticosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Diagnóstico no encontrado con id: " + id);
        }
        diagnosticosRepository.deleteById(id);
    }
/*
    private DiagnosticosDTO toDTO(Diagnosticos diagnostico) {
        DiagnosticosDTO dto = new DiagnosticosDTO();
        dto.setIdDiagnostico(diagnostico.getIdDiagnostico());
        dto.setNombre(diagnostico.getNombre());
        dto.setDescripcion(diagnostico.getDescripcion());
        return dto;
    }
}
