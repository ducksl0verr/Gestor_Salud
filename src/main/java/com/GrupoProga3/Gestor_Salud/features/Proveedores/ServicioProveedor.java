package com.GrupoProga3.Gestor_Salud.features.Proveedores;

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

    @Override
    public ProveedorRespuesta crear(ProveedorNuevo nuevo) {
        System.out.println(nuevo);
        EntidadProveedor proveedor = repositorioProveedor.save(proveedorMapper.toEntity(nuevo));
        System.out.println(proveedor);
        return proveedorMapper.toDTO(proveedor);
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

        buscado.setContacto(proveedorMapper.toEntity(nuevo.contacto()));
        buscado.setNombre(nuevo.nombre());

        List<EntidadDomicilio> domicilios = nuevo.direccion()
                .stream()
                .map(proveedorMapper::toEntity)
                .toList();

        buscado.getDireccion().clear();
        buscado.getDireccion().addAll(domicilios);

        EntidadProveedor actualizado = repositorioProveedor.save(buscado);

        System.out.println(actualizado);

        return proveedorMapper.toDTO(actualizado);
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
