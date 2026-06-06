package com.GrupoProga3.Gestor_Salud.Gastos;

import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs.GastoActualizar;
import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs.GastoNuevo;
import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs.GastoRespuesta;
import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.EntidadGasto;
import com.GrupoProga3.Gestor_Salud.Gastos.Dominio.MAPPER.GastoMapper;
import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.EntidadProveedor;
import com.GrupoProga3.Gestor_Salud.Proveedores.RepositorioProveedor;
import com.GrupoProga3.Gestor_Salud.common.excepciones.Gastos.GastoNoEncontradoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.Proveedores.ProveedorNoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioGasto implements IServicioGasto {

    private final RepositorioGasto repositorioGasto;
    private final RepositorioProveedor  repositorioProveedor;
    private final GastoMapper gastoMapper;

    @Override
    public GastoRespuesta crear(GastoNuevo gastoNuevo) {
        System.out.println(gastoNuevo);

        EntidadGasto gasto = gastoMapper.toEntity(gastoNuevo);

        EntidadProveedor proveedor = repositorioProveedor.findAll()
                .stream()
                .filter(p-> p.getNombre().equalsIgnoreCase(gastoNuevo.nombreProveedor()))
                .findFirst()
                .orElseThrow(()-> new ProveedorNoEncontradoException("No se encontró ningún proveedor con aquel nombre"));
        gasto.setProveedor(proveedor);

        EntidadGasto creado = repositorioGasto.save(gasto);
        System.out.println(creado);

        return gastoMapper.toDTO(creado);
    }

    @Override
    public GastoRespuesta buscarPorId(Long id) {
        EntidadGasto gasto = repositorioGasto
                .findById(id)
                .orElseThrow(() -> new GastoNoEncontradoException("No existe tal gasto"));
        return gastoMapper.toDTO(gasto);
    }

    @Override
    public List<GastoRespuesta> buscarTodos() {
        return repositorioGasto
                .findAll()
                .stream()
                .map(gastoMapper::toDTO)
                .toList();
    }

    @Override
    public List<GastoRespuesta> buscarPorProveedor(String nombre) {
        return repositorioGasto
                .findAll()
                .stream()
                .filter(g->g.getProveedor().getNombre().equalsIgnoreCase(nombre))
                .map(gastoMapper::toDTO)
                .toList();
    }

    @Override
    public GastoRespuesta actualizar(Long id, GastoActualizar gastoActualizar) {
        EntidadGasto gasto = repositorioGasto
                .findById(id)
                .orElseThrow(() -> new GastoNoEncontradoException("No existe tal gasto"));

        gasto.setDescripcion(gastoActualizar.descripcion());
        gasto.setTipoGasto(gastoActualizar.tipoGasto());
        gasto.setMonto(gastoActualizar.monto());
        gasto.setFecha(gastoActualizar.fecha());
        gasto.setObservaciones(gastoActualizar.observaciones());
        gasto.setMetodoPago(gastoActualizar.metodoPago());

        EntidadGasto actualizado = repositorioGasto.save(gasto);

        return gastoMapper.toDTO(actualizado);
    }

    @Override
    public void borrar(Long id) {
        EntidadGasto gasto = repositorioGasto
                .findById(id)
                .orElseThrow(() -> new GastoNoEncontradoException("No existe tal gasto"));

        repositorioGasto.delete(gasto);
    }

}
