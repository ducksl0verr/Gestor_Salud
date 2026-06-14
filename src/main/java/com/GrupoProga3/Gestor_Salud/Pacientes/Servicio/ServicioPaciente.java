package com.GrupoProga3.Gestor_Salud.Pacientes.Servicio;

import com.GrupoProga3.Gestor_Salud.Contacto.Repositorio.RepositorioContacto;
import com.GrupoProga3.Gestor_Salud.ObraSocial.EntidadObraSocial;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.RecursoExistenteException;
import com.GrupoProga3.Gestor_Salud.ObraSocial.RepositorioObraSocial;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteActualizar;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteNuevo;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.Mapper.PacienteMapper;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioPaciente implements IServicioPaciente{

    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioContacto repositorioContacto;
    private final RepositorioObraSocial repositorioObraSocial;
    private final PacienteMapper pacienteMapper;

    @Override
    public PacienteRespuesta guardar(PacienteNuevo pacienteNuevo) {
        System.out.println(pacienteNuevo);
        EntidadPaciente buscado = pacienteMapper.toEntity(pacienteNuevo);

        EntidadObraSocial obraSocial = repositorioObraSocial
                .findById(pacienteNuevo.id_obraSocial())
                        .orElseThrow(()->new EntidadNoEncontradaException(
                                "Obra Social",
                                "No encontrada",
                                pacienteNuevo.id_obraSocial(),
                                "No se ha encontrado ninguna obra social con ese ID."
                        ));

        buscado.setObraSocial(obraSocial);

        if(repositorioContacto.existsByTelefono(pacienteNuevo.contacto().telefono()))
        {
            throw new RecursoExistenteException("Ya existe un contacto con este telefono: "
            + pacienteNuevo.contacto().telefono());
        }

        if (repositorioContacto.existsByEmail(pacienteNuevo.contacto().email())){
            throw new RecursoExistenteException("Ya existe un contacto con este email: "+ pacienteNuevo.contacto().email());
        }

        EntidadPaciente guardado = repositorioPaciente.save(buscado);

        return pacienteMapper.toDTO(guardado);
    }

    @Override
    public void borrar(Long id) {
        repositorioPaciente.findById(id)
                .ifPresent(repositorioPaciente::delete);
    }

    @Override
    public PacienteRespuesta actualizar(Long id, PacienteActualizar paciente) {
        EntidadPaciente pac = repositorioPaciente.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Paciente",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado a ningún paciente con aquel ID."
                ));

        pac.setNombre(paciente.nombre());
        pac.setApellido(paciente.apellido());
        //pac.setFecha_nacimiento(paciente.fecha_nacimiento());

        EntidadObraSocial obraSocial = repositorioObraSocial
                .findById(paciente.id_obraSocial())
                .orElseThrow(()->new EntidadNoEncontradaException(
                        "Obra Social",
                        "No encontrada",
                        paciente.id_obraSocial(),
                        "No se ha encontrado ninguna obra social con ese ID."
                ));

        pac.setObraSocial(obraSocial);
        pac.setContacto(pacienteMapper.toEntity(paciente.contacto()));

        EntidadPaciente actualizado = repositorioPaciente.save(pac);
        return pacienteMapper.toDTO(actualizado);
    }

    @Override
    public PacienteRespuesta buscarPorid(Long id) {
        return repositorioPaciente.findById(id)
                .map(pacienteMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public List<PacienteRespuesta> buscarTodos() {
        return repositorioPaciente.findAll().stream()
                .map(pacienteMapper::toDTO)
                .toList();
    }

}
