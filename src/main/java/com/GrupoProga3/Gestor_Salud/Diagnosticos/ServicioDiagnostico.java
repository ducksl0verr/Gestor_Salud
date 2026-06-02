package com.GrupoProga3.Gestor_Salud.Diagnosticos;

import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs.DiagnosticoNuevo;
import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;
import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.EntidadDiagnostico;
import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.MAPPER.DiagnosticoMapper;
import com.GrupoProga3.Gestor_Salud.common.DiagnosticoNoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioDiagnostico implements IServicioDiagnostico {
    private final RepositorioDiagnostico repositorioDiagnostico;
    private final DiagnosticoMapper  diagnosticoMapper;

    @Override
    public DiagnosticoRespuesta crear(DiagnosticoNuevo diagnosticoNuevo) {
        System.out.println(diagnosticoNuevo);
        EntidadDiagnostico diagnostico=repositorioDiagnostico.save(diagnosticoMapper.toEntity(diagnosticoNuevo));
        System.out.println(diagnostico);
        return diagnosticoMapper.toDTO(diagnostico);
    }

    @Override
    public List<DiagnosticoRespuesta> buscarTodos() {
        return repositorioDiagnostico
                .findAll()
                .stream()
                .map(diagnosticoMapper::toDTO)
                .toList();
    }

    @Override
    public DiagnosticoRespuesta buscarPorId(Long id) {
        EntidadDiagnostico buscado = repositorioDiagnostico
                .findById(id)
                .orElseThrow(()-> new DiagnosticoNoEncontradoException("No se ha encontrado aquel diagnostico"));
        return diagnosticoMapper.toDTO(buscado);
    }

    @Override
    public DiagnosticoRespuesta actualizar(Long id, DiagnosticoNuevo diagnosticoNuevo) {
        EntidadDiagnostico buscado = repositorioDiagnostico
                .findById(id)
                .orElseThrow(()-> new DiagnosticoNoEncontradoException("No se ha encontrado aquel diagnostico"));
        buscado.setNombre(diagnosticoNuevo.nombre());
        buscado.setDescripcion(diagnosticoNuevo.descripcion());
        EntidadDiagnostico actualizado=repositorioDiagnostico.save(buscado);
        return diagnosticoMapper.toDTO(actualizado);
    }

    @Override
    public void borrar(Long id) {
        EntidadDiagnostico buscado = repositorioDiagnostico
                .findById(id)
                .orElseThrow(()-> new DiagnosticoNoEncontradoException("No se ha encontrado aquel diagnostico"));
        repositorioDiagnostico.delete(buscado);
    }
}
