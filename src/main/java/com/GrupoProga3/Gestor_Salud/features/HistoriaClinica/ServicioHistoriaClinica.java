package com.GrupoProga3.Gestor_Salud.features.HistoriaClinica;

import com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.DTOs.DiagnosticoDeHistoriaNueva;
import com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.DTOs.HistoriaClinicaActualizar;
import com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.DTOs.HistoriaClinicaNueva;
import com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.DTOs.HistoriaClinicaRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.EntidadDiagnostico;
import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.RepositorioDiagnostico;
import com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.EntidadHistoriaClinica;
import com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.MAPPER.HistoriaClinicaMapper;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Repositorio.RepositorioUsuario;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServicioHistoriaClinica implements IServicioHistoriaClinica {
    private final RepositorioHistoriaClinica repositorioHistoriaClinica;
    /// No sé si es correcto que se instancien estos repositorios de usuario y paciente
    /// en el servioio, preguntar al profe.
    private final RepositorioUsuario usuarioRepositorio;
    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioDiagnostico repositorioDiagnostico;
    private final HistoriaClinicaMapper historiaClinicaMapper;


    @Override
    @Transactional
    public HistoriaClinicaRespuesta crear (HistoriaClinicaNueva historiaClinica){
        System.out.println(historiaClinica);
        EntidadHistoriaClinica historial= historiaClinicaMapper.toEntity(historiaClinica);

        /// Esto es para q se mapeen los ids a las entidades que hacen referencia correctamente
        EntidadUsuarios profesional = usuarioRepositorio
                .findById(historiaClinica.id_profesional())
                        .orElseThrow(()->new EntidadNoEncontradaException(
                                "Profesional",
                                "No se ha encontrado.",
                                historiaClinica.id_profesional(),
                                "No se ha encontrado a ningún profesional con aquel ID."
                        ));
        historial.setProfesional(profesional);

        EntidadPaciente paciente = repositorioPaciente
                .findById(historiaClinica.id_paciente())
                .orElseThrow(()->new EntidadNoEncontradaException(
                        "Paciente",
                        "No se ha encontrado.",
                        historiaClinica.id_paciente(),
                        "No se ha encontrado a ningún paciente con aquel ID."
                ));
        historial.setPaciente(paciente);

        historial.setDiagnosticos(new ArrayList<>());
        if (historiaClinica.ids_diagnosticos() != null) {
            for (Long idDiagnostico : historiaClinica.ids_diagnosticos()) {
                EntidadDiagnostico diagnostico = repositorioDiagnostico
                        .findById(idDiagnostico)
                        .orElseThrow(() -> new EntidadNoEncontradaException(
                                "Diagnostico",
                                "No encontrado",
                                idDiagnostico,
                                "No se ha encontrado ningún diagnostico con aquel ID."
                        ));
                diagnostico.setHistoriaClinica(historial);
                historial.getDiagnosticos().add(diagnostico);
            }
        }

        EntidadHistoriaClinica guardado = repositorioHistoriaClinica.save(historial);

        /// Se crean los diagnosticos nuevos asociados a la historia recien guardada.
        if (historiaClinica.diagnosticos() != null) {
            for (DiagnosticoDeHistoriaNueva nuevoDiagnostico : historiaClinica.diagnosticos()) {
                EntidadDiagnostico diagnostico = new EntidadDiagnostico();
                diagnostico.setNombre(nuevoDiagnostico.nombre());
                diagnostico.setDescripcion(nuevoDiagnostico.descripcion());
                diagnostico.setFechaDiagnostico(nuevoDiagnostico.fechaDiagnostico());
                diagnostico.setHistoriaClinica(guardado);

                EntidadDiagnostico diagnosticoGuardado = repositorioDiagnostico.save(diagnostico);
                guardado.getDiagnosticos().add(diagnosticoGuardado);
            }
        }

        System.out.println(guardado);
        return historiaClinicaMapper.toDTO(guardado);
    }

    public List<HistoriaClinicaRespuesta> buscarTodos(){
        return repositorioHistoriaClinica.findAll()
                .stream()
                .map(historiaClinicaMapper::toDTO)
                .toList();
    }

    public HistoriaClinicaRespuesta buscarPorId(Long id){
        EntidadHistoriaClinica buscado = repositorioHistoriaClinica.findById(id)
                .orElseThrow(()->new EntidadNoEncontradaException(
                        "Historia Clinica",
                        "No encontrada",
                        id,
                        "No se ha encontrado la historia clinica."
                ));
        return historiaClinicaMapper.toDTO(buscado);
    }

    @Override
    @Transactional
    public HistoriaClinicaRespuesta actualizar (Long id, HistoriaClinicaActualizar historiaClinica){
        EntidadHistoriaClinica buscado = repositorioHistoriaClinica
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Historia Clinica",
                        "No encontrada",
                        id,
                        "No se ha encontrado la historia clinica."
                ));
        buscado.setEvolucion(historiaClinica.evolucion());
        buscado.setObservaciones(historiaClinica.observaciones());
        EntidadHistoriaClinica actualizado = repositorioHistoriaClinica.save(buscado);
        return historiaClinicaMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void borrar(Long id){
        EntidadHistoriaClinica buscado = repositorioHistoriaClinica
                .findById(id)
                .orElseThrow(()->new EntidadNoEncontradaException(
                        "Historia Clinica",
                        "No encontrada",
                        id,
                        "No se ha encontrado la historia clinica."
                ));
        repositorioHistoriaClinica.delete(buscado);
    }

}
