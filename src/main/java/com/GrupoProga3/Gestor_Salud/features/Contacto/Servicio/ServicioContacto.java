package com.GrupoProga3.Gestor_Salud.features.Contacto.Servicio;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.Mappers.ContactoMapper;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Repositorio.RepositorioContacto;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.validacion.ServicioValidacionUnicidad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServicioContacto implements IServicioContacto {

    private final RepositorioContacto repositorioContacto;
    private final ContactoMapper contactoMapper;
    private final ServicioValidacionUnicidad validacionUnicidad;

    @Override
    @Transactional
    public ContactoRespuesta guardar(ContactoNuevo contactoNuevo) {

        EntidadContacto contacto = contactoMapper.toEntity(contactoNuevo);
        validacionUnicidad.normalizarYValidarContacto(contacto, null);

        EntidadContacto guardado = repositorioContacto.save(contacto);

        return contactoMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public ContactoRespuesta actualizar(Long id,
                                        ContactoNuevo contactoNuevo) {

        EntidadContacto contacto =
                repositorioContacto.findById(id)
                        .orElseThrow(() ->
                                new EntidadNoEncontradaException(
                                        "Contacto",
                                        "No encontrado",
                                        id,
                                        "No se ha encontrado ninguna contacto con aquel ID."
                                ));

        if (contactoNuevo.telefono() != null && !contactoNuevo.telefono().isBlank()) {
            contacto.setTelefono(contactoNuevo.telefono());
        }

        if (contactoNuevo.email() != null && !contactoNuevo.email().isBlank()) {
            contacto.setEmail(contactoNuevo.email());
        }

        validacionUnicidad.normalizarYValidarContacto(contacto, contacto.getId());

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
                                new EntidadNoEncontradaException(
                                        "Contacto",
                                        "No encontrado",
                                        id,
                                        "No se ha encontrado ningún contacto con aquel ID."
                                ));

        repositorioContacto.delete(contacto);
    }

    /*public List<ContactoRespuesta> buscarContacto(
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

     */

    @Override
    @Transactional(readOnly = true)
    public ContactoRespuesta buscarPorId(Long id) {

        EntidadContacto contacto =
                repositorioContacto.findById(id)
                        .orElseThrow(() ->
                                new EntidadNoEncontradaException(
                                        "Contacto",
                                        "No encontrado",
                                        id,
                                        "No se ha encontrado ningún contacto con aquel ID."
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

