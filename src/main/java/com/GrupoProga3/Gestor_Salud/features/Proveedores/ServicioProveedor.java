package com.GrupoProga3.Gestor_Salud.features.Proveedores;

import com.GrupoProga3.Gestor_Salud.common.validacion.ServicioValidacionUnicidad;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs.ProveedorActualizar;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs.ProveedorNuevo;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs.ProveedorRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.EntidadProveedor;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.MAPPER.ProveedorMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioProveedor implements IServicioProveedor{
    private final RepositorioProveedor repositorioProveedor;
    private final ProveedorMapper  proveedorMapper;
    private final ServicioValidacionUnicidad validacionUnicidad;

    @Override
    public ProveedorRespuesta crear(ProveedorNuevo nuevo) {
        EntidadProveedor proveedor = proveedorMapper.toEntity(nuevo);
        proveedor.setCuil(validacionUnicidad.normalizarYValidarCuil(proveedor.getCuil(), null));
        validacionUnicidad.normalizarYValidarContacto(proveedor.getContacto(), null);

        EntidadProveedor guardado = repositorioProveedor.save(proveedor);
        return proveedorMapper.toDTO(guardado);
    }

    @Override
    public ProveedorRespuesta buscarPorId(Long id) {
        EntidadProveedor buscado = repositorioProveedor
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("Proveedor",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún proveedor con aquel ID."));
        return proveedorMapper.toDTO(buscado);
    }

    @Override
    public List<ProveedorRespuesta> buscarTodos() {
        return repositorioProveedor
                .findAll()
                .stream()
                .map(proveedorMapper::toDTO)
                .toList();
    }

    @Override
    public ProveedorRespuesta buscarPorNombre(String nombre) {
        EntidadProveedor buscado = repositorioProveedor
                .findAll()
                .stream()
                .filter(p-> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(()-> new EntidadNoEncontradaException("Usuario",
                        "No se ha encontrado.",
                        1l,
                        "No se ha encontrado ningún proveedor con aquel nombre."));
        return proveedorMapper.toDTO(buscado);
    }

    @Override
    public ProveedorRespuesta actualizar(Long id, ProveedorActualizar nuevo) {
        EntidadProveedor buscado = repositorioProveedor
            .findById(id)
            .orElseThrow(()-> new EntidadNoEncontradaException("Proveedor",
                    "No se ha encontrado.",
                    id,
                    "No se ha encontrado ningún proveedor con aquel ID."));

        EntidadContacto contactoActual = buscado.getContacto();
        String emailAnterior = contactoActual == null ? null : contactoActual.getEmail();
        String telefonoAnterior = contactoActual == null ? null : contactoActual.getTelefono();

        EntidadContacto contacto = actualizarContacto(contactoActual, nuevo.contacto());
        validacionUnicidad.normalizarYValidarContacto(
                contacto, contacto == null ? null : contacto.getId(), emailAnterior, telefonoAnterior);
        buscado.setContacto(contacto);
        buscado.setNombre(nuevo.nombre());

        List<EntidadDomicilio> domicilios = nuevo.direccion()
                .stream()
                .map(proveedorMapper::toEntity)
                .toList();

        buscado.getDireccion().clear();
        buscado.getDireccion().addAll(domicilios);

        EntidadProveedor actualizado = repositorioProveedor.save(buscado);

        return proveedorMapper.toDTO(actualizado);
    }

    /** Actualiza el contacto existente en lugar de crear uno nuevo en cada edición. */
    private EntidadContacto actualizarContacto(EntidadContacto existente, ContactoNuevo datos) {
        if (datos == null) {
            return existente;
        }

        EntidadContacto contacto = existente == null ? new EntidadContacto() : existente;
        contacto.setEmail(datos.email());
        contacto.setTelefono(datos.telefono());
        return contacto;
    }

    @Override
    public void borrar(Long id) {
        EntidadProveedor buscado = repositorioProveedor
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("Proveedor",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ningún proveedor con aquel ID."));

        repositorioProveedor.delete(buscado);
    }
}
