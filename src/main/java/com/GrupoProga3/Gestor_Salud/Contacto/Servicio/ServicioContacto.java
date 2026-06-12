package com.GrupoProga3.Gestor_Salud.Contacto.Servicio;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoDTO;
import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoRespuesta;
import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.Mappers.ContactoMapper;
import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.Contacto.Repositorio.RepositorioContacto;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.RecursoExistenteException;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.RecursoNoEncontradoException;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.ReglaNegocioException;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio.RepositorioPaciente;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServicioContacto implements IServicioContacto {

    private final RepositorioContacto repositorioContacto;
    private final RepositorioPaciente repositorioPaciente;
    private final ContactoMapper contactoMapper;

    @Override
    @Transactional
    public ContactoRespuesta guardar(ContactoNuevo contactoNuevo) {

        if (repositorioContacto.existsByTelefono(contactoNuevo.telefono())) {
            throw new RecursoExistenteException(
                    "Ya existe un contacto con teléfono: "
                            + contactoNuevo.telefono()
            );
        }

        if (repositorioContacto.existsByPacienteId(contactoNuevo.idPaciente())) {
            throw new ReglaNegocioException(
                    "El paciente ya posee un contacto asociado."
            );
        }

        EntidadPaciente paciente = repositorioPaciente
                .findById(contactoNuevo.idPaciente())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "El paciente con id "
                                        + contactoNuevo.idPaciente()
                                        + " no existe."
                        ));

        EntidadContacto contacto =
                contactoMapper.toEntity(contactoNuevo);

        contacto.setPaciente(paciente);

        EntidadContacto guardado =
                repositorioContacto.save(contacto);

        return contactoMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public ContactoRespuesta actualizar(Long id,
                                        ContactoNuevo contactoNuevo) {

        EntidadContacto contacto =
                repositorioContacto.findById(id)
                        .orElseThrow(() ->
                                new RecursoNoEncontradoException(
                                        "El contacto con id "
                                                + id
                                                + " no existe."
                                ));

        if (contactoNuevo.nombre() != null
                && !contactoNuevo.nombre().isBlank()) {

            contacto.setNombre(contactoNuevo.nombre());
        }

        if (contactoNuevo.apellido() != null
                && !contactoNuevo.apellido().isBlank()) {

            contacto.setApellido(contactoNuevo.apellido());
        }

        if (contactoNuevo.telefono() != null
                && !contactoNuevo.telefono().isBlank()) {

            if (!contacto.getTelefono().equals(contactoNuevo.telefono())
                    && repositorioContacto.existsByTelefono(
                    contactoNuevo.telefono())) {

                throw new ReglaNegocioException(
                        "Ya existe un contacto con teléfono: "
                                + contactoNuevo.telefono()
                );
            }

            contacto.setTelefono(contactoNuevo.telefono());
        }

        EntidadContacto actualizado =
                repositorioContacto.save(contacto);

        return contactoMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {

        EntidadContacto contacto =
                repositorioContacto.findById(id)
                        .orElseThrow(() ->
                                new RecursoNoEncontradoException(
                                        "El contacto con id "
                                                + id
                                                + " no existe."
                                ));

        repositorioContacto.delete(contacto);
    }

    public List<ContactoRespuesta> buscarContacto(
            String nombre,
            String apellido) {

        if (nombre != null && apellido != null) {
            return repositorioContacto
                    .findByNombreContainingAndApellidoContaining(
                            nombre,
                            apellido)
                    .stream()
                    .map(contactoMapper::toDTO)
                    .toList();
        }

        if (nombre != null) {
            return repositorioContacto
                    .findByNombreContaining(nombre)
                    .stream()
                    .map(contactoMapper::toDTO)
                    .toList();
        }

        if (apellido != null) {
            return repositorioContacto
                    .findByApellidoContaining(apellido)
                    .stream()
                    .map(contactoMapper::toDTO)
                    .toList();
        }

        return repositorioContacto.findAll()
                .stream()
                .map(contactoMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ContactoRespuesta buscarPorId(Long id) {

        EntidadContacto contacto =
                repositorioContacto.findById(id)
                        .orElseThrow(() ->
                                new RecursoNoEncontradoException(
                                        "El contacto con id "
                                                + id
                                                + " no existe."
                                ));

        return contactoMapper.toDTO(contacto);
    }

    @Override
    @Transactional
    public List<ContactoRespuesta> buscarTodos() {

        return repositorioContacto.findAll()
                .stream()
                .map(contactoMapper::toDTO)
                .toList();
    }
}

