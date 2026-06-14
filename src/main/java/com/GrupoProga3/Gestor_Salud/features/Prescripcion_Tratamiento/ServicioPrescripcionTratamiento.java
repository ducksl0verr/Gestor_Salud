package com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento;

import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.DTOs.PrescripcionTratamientoNueva;
import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.DTOs.PrescripcionTratamientoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.EntidadPrescripcionTratamiento;
import com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.MAPPER.PrescripcionTratamientoMapper;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.Doiminio.EntidadTratamiento;
import com.GrupoProga3.Gestor_Salud.features.Tratamientos.RepositorioTratamiento;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Repositorio.RepositorioUsuario;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioPrescripcionTratamiento implements IServicioPrescripcionTratamiento {

    private final RepositorioPrescripcionTratamiento repositorioPrescripcionTratamiento;
    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioTratamiento repositorioTratamiento;
    private final PrescripcionTratamientoMapper prescripcionTratamientoMapper;


    public void validarTratamiento (EntidadPrescripcionTratamiento pre){
        boolean esiste = repositorioPrescripcionTratamiento
                .existsByPacienteAndTratamientosAndActivoTrue(pre.getPaciente().getId(),
                        pre.getTratamientos().getId());
        if(esiste){
           throw new RecursoOcupadoException(
                   "El pacienta ya tiene este tratamiento activo."
           );
        }
    }

    @Override
    @Transactional
    public PrescripcionTratamientoRespuesta crear(PrescripcionTratamientoNueva prescripcionTratamiento) {
        System.out.println(prescripcionTratamiento);
        EntidadPrescripcionTratamiento pres = prescripcionTratamientoMapper.toEntity(prescripcionTratamiento);

        EntidadPaciente pac = repositorioPaciente
                .findById(prescripcionTratamiento.id_paciente())
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Paciente",
                        "No encontrado",
                        prescripcionTratamiento.id_paciente(),
                        "No se encontró ningún paciente con aquel ID."
                ));

        EntidadTratamiento tra = repositorioTratamiento
                .findById(prescripcionTratamiento.id_tratamiento())
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Tratamiento",
                        "No encontrado",
                        prescripcionTratamiento.id_tratamiento(),
                        "No se encontró ninún tratamiento con aquel ID."
                ));

        EntidadUsuarios prof = repositorioUsuario
                .findById(prescripcionTratamiento.id_profesional())
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Profesional",
                        "No encontrado",
                        prescripcionTratamiento.id_profesional(),
                        "No se ha encontrado ningún profesional con aquel ID."
                ));
        validarTratamiento(pres);

        pres.setPaciente(pac);
        pres.setTratamientos(tra);
        pres.setProfesional(prof);

        pres.setActivo(true);

        EntidadPrescripcionTratamiento guardado=repositorioPrescripcionTratamiento.save(pres);

        return prescripcionTratamientoMapper.toDTO(guardado);
    }

    @Override
    public PrescripcionTratamientoRespuesta buscarPorId(Long id) {
        EntidadPrescripcionTratamiento pres = repositorioPrescripcionTratamiento
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Prescripción de Tratamiento",
                        "No encontrada",
                        id,
                        "No se encontró ninguna prescripción con aquel ID."
                ));
        return prescripcionTratamientoMapper.toDTO(pres);
    }

    @Override
    public List<PrescripcionTratamientoRespuesta> buscarTodos() {
        return repositorioPrescripcionTratamiento
                .findAll()
                .stream()
                .map(prescripcionTratamientoMapper::toDTO)
                .toList();
    }

    @Override
    public PrescripcionTratamientoRespuesta pararTratamiento(Long id) {
        EntidadPrescripcionTratamiento pres = repositorioPrescripcionTratamiento
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Prescripción de Tratamiento",
                        "No encontrada",
                        id,
                        "No se encontró ninguna prescripción con aquel ID."
                ));
        pres.setActivo(false);
        EntidadPrescripcionTratamiento baja = repositorioPrescripcionTratamiento
                .save(pres);

        return prescripcionTratamientoMapper.toDTO(baja);
    }
}
