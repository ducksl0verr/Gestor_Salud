package com.GrupoProga3.Gestor_Salud.features.Pacientes.Servicio;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Repositorio.RepositorioContacto;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.features.ObraSocial.Dominio.EntidadObraSocial;
import com.GrupoProga3.Gestor_Salud.features.ObraSocial.RepositorioObraSocial;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteActualizar;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteNuevo;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.Mapper.PacienteMapper;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioPaciente implements IServicioPaciente {

    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioContacto repositorioContacto;
    private final RepositorioObraSocial repositorioObraSocial;
    private final PacienteMapper pacienteMapper;

    @Override
    @Transactional
    public PacienteRespuesta guardar(PacienteNuevo pacienteNuevo) {

        if (repositorioPaciente.existsByDni(pacienteNuevo.dni())) {
            throw new RecursoOcupadoException(
                    "Ya existe un paciente con este DNI: " + pacienteNuevo.dni());
        }

        if (repositorioPaciente.existsByNumeroAfiliado(pacienteNuevo.numeroAfiliado())) {
            throw new RecursoOcupadoException(
                    "Ya existe un paciente con este número de afiliado: "
                            + pacienteNuevo.numeroAfiliado());
        }

        if (repositorioContacto.existsByTelefono(pacienteNuevo.contacto().telefono())) {
            throw new RecursoOcupadoException(
                    "Ya existe un contacto con este teléfono: "
                            + pacienteNuevo.contacto().telefono());
        }

        if (repositorioContacto.existsByEmail(pacienteNuevo.contacto().email())) {
            throw new RecursoOcupadoException(
                    "Ya existe un contacto con este email: "
                            + pacienteNuevo.contacto().email());
        }

        EntidadPaciente buscado = pacienteMapper.toEntity(pacienteNuevo);
        buscado.setDomicilio(normalizarDomicilio(buscado.getDomicilio()));
        buscado.setObraSocial(buscarObraSocial(pacienteNuevo.id_obraSocial()));

        EntidadPaciente guardado = repositorioPaciente.save(buscado);

        return pacienteMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        EntidadPaciente paciente = repositorioPaciente.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Paciente",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado a ningún paciente con aquel ID."
                ));

        try {
            repositorioPaciente.delete(paciente);
            repositorioPaciente.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new RecursoOcupadoException(
                    "No se puede eliminar el paciente porque tiene datos asociados "
                            + "(turnos, recetas, historia clínica, facturas, cirugías o tratamientos)."
            );
        }
    }

    @Override
    @Transactional
    public PacienteRespuesta actualizar(Long id, PacienteActualizar paciente) {
        EntidadPaciente pac = repositorioPaciente.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Paciente",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado a ningún paciente con aquel ID."
                ));

        if (repositorioPaciente.existsByDniAndIdNot(paciente.dni(), id)) {
            throw new RecursoOcupadoException(
                    "Ya existe otro paciente con este DNI: " + paciente.dni());
        }

        if (repositorioPaciente.existsByNumeroAfiliadoAndIdNot(paciente.numeroAfiliado(), id)) {
            throw new RecursoOcupadoException(
                    "Ya existe otro paciente con este número de afiliado: "
                            + paciente.numeroAfiliado());
        }

        validarContactoDuplicado(pac.getContacto(), paciente.contacto());

        pac.setNombre(paciente.nombre());
        pac.setApellido(paciente.apellido());
        pac.setFecha_nacimiento(paciente.fecha_nacimiento());
        pac.setDni(paciente.dni());
        pac.setNumeroAfiliado(paciente.numeroAfiliado());
        pac.setObraSocial(buscarObraSocial(paciente.id_obraSocial()));
        pac.setContacto(actualizarContacto(pac.getContacto(), paciente.contacto()));
        pac.setDomicilio(actualizarDomicilio(pac.getDomicilio(), paciente.domicilio()));

        EntidadPaciente actualizado = repositorioPaciente.save(pac);

        return pacienteMapper.toDTO(actualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteRespuesta buscarPorid(Long id) {
        return repositorioPaciente.findById(id)
                .map(pacienteMapper::toDTO)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Paciente",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado a ningún paciente con aquel ID."
                ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PacienteRespuesta> buscarTodos() {
        return repositorioPaciente.findAll().stream()
                .map(pacienteMapper::toDTO)
                .toList();
    }

    private EntidadObraSocial buscarObraSocial(Long idObraSocial) {
        return repositorioObraSocial.findById(idObraSocial)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Obra Social",
                        "No encontrada",
                        idObraSocial,
                        "No se ha encontrado ninguna obra social con ese ID."
                ));
    }

    private void validarContactoDuplicado(EntidadContacto existente, ContactoNuevo datos) {
        Long idContacto = existente == null ? null : existente.getId();

        boolean telefonoRepetido = idContacto == null
                ? repositorioContacto.existsByTelefono(datos.telefono())
                : repositorioContacto.existsByTelefonoAndIdNot(datos.telefono(), idContacto);

        if (telefonoRepetido) {
            throw new RecursoOcupadoException(
                    "Ya existe otro contacto con este teléfono: " + datos.telefono());
        }

        boolean emailRepetido = idContacto == null
                ? repositorioContacto.existsByEmail(datos.email())
                : repositorioContacto.existsByEmailAndIdNot(datos.email(), idContacto);

        if (emailRepetido) {
            throw new RecursoOcupadoException(
                    "Ya existe otro contacto con este email: " + datos.email());
        }
    }

    private EntidadContacto actualizarContacto(EntidadContacto existente, ContactoNuevo datos) {
        EntidadContacto contacto = existente == null ? new EntidadContacto() : existente;

        contacto.setEmail(datos.email());
        contacto.setTelefono(datos.telefono());

        return contacto;
    }

    private EntidadDomicilio actualizarDomicilio(EntidadDomicilio existente, DomicilioNuevo datos) {
        EntidadDomicilio domicilio = existente == null ? new EntidadDomicilio() : existente;

        domicilio.setCalle(datos.calle());
        domicilio.setNumero(datos.numero());
        domicilio.setPiso(datos.piso() == null ? "" : datos.piso());
        domicilio.setDepto(datos.depto() == null ? "" : datos.depto());
        domicilio.setLocalidad(datos.localidad());
        domicilio.setProvincia(datos.provincia());
        domicilio.setCodigo_postal(datos.codigo_postal());

        return domicilio;
    }

    private EntidadDomicilio normalizarDomicilio(EntidadDomicilio domicilio) {
        if (domicilio == null) {
            return null;
        }

        if (domicilio.getPiso() == null) {
            domicilio.setPiso("");
        }

        if (domicilio.getDepto() == null) {
            domicilio.setDepto("");
        }

        return domicilio;
    }
}
