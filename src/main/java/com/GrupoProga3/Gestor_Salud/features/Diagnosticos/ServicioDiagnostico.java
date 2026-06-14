package com.GrupoProga3.Gestor_Salud.features.Diagnosticos;

import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs.DiagnosticoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.EntidadDiagnostico;
import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.MAPPER.DiagnosticoMapper;
import com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.EntidadHistoriaClinica;
import com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.RepositorioHistoriaClinica;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioDiagnostico implements IServicioDiagnostico {
    private final RepositorioDiagnostico repositorioDiagnostico;
    private final RepositorioHistoriaClinica repositorioHistoriaClinica;
    private final DiagnosticoMapper  diagnosticoMapper;

    @Override
    @Transactional
    public DiagnosticoRespuesta crear(DiagnosticoNuevo diagnosticoNuevo) {
        System.out.println(diagnosticoNuevo);
        EntidadDiagnostico diagnostico=diagnosticoMapper.toEntity(diagnosticoNuevo);
        EntidadHistoriaClinica hc= repositorioHistoriaClinica
                .findById(diagnosticoNuevo.idHistoriaClinica())
                        .orElseThrow(()-> new EntidadNoEncontradaException(
                                "Historia Clinica",
                                "No encontrada",
                                diagnosticoNuevo.idHistoriaClinica(),
                                "No se ha encontrado ninguna historia clinica con aquel ID."
                        ));

        diagnostico.setHistoriaClinica(hc);
        hc.getDiagnosticos().add(diagnostico);

        EntidadDiagnostico guardado = repositorioDiagnostico.save(diagnostico);

        System.out.println(guardado);
        return diagnosticoMapper.toDTO(guardado);
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
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Diagnostico",
                        "No encontrado",
                        id,
                        "No se ha encontrado ningún diagnostico con aquel ID."
                ));
        return diagnosticoMapper.toDTO(buscado);
    }

    @Override
    @Transactional
    public DiagnosticoRespuesta actualizar(Long id, DiagnosticoNuevo diagnosticoNuevo) {
        EntidadDiagnostico buscado = repositorioDiagnostico
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Diagnostico",
                        "No encontrado",
                        id,
                        "No se ha encontrado ningún diagnostico con aquel ID."
                ));
        buscado.setNombre(diagnosticoNuevo.nombre());
        buscado.setDescripcion(diagnosticoNuevo.descripcion());
        EntidadDiagnostico actualizado=repositorioDiagnostico.save(buscado);
        return diagnosticoMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        EntidadDiagnostico buscado = repositorioDiagnostico
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Diagnostico",
                        "No encontrado",
                        id,
                        "No se ha encontrado ningún diagnostico con aquel ID."
                ));
        repositorioDiagnostico.delete(buscado);
    }
}
