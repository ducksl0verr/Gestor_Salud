package com.GrupoProga3.Gestor_Salud.Facturas.Servicio;

import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaDTO;
import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.Mapper.FacturaMapper;
import com.GrupoProga3.Gestor_Salud.Facturas.Exceptions.FacturaNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.Facturas.Model.EntidadFacturas;
import com.GrupoProga3.Gestor_Salud.Facturas.Repositorio.RepositorioFactura;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioFactura implements IServicioFactura{

    private final RepositorioFactura repositorioFactura;
    private final FacturaMapper facturaMapper;

    @Override
    public FacturaDTO guardar(FacturaDTO facturaDTO) {
        EntidadFacturas guardada = repositorioFactura.save(facturaMapper.toEntity(facturaDTO));
        return facturaMapper.toDTO(guardada);
    }

    @Override
    public void borrar(Long id) {
        EntidadFacturas factura = repositorioFactura.findById(id)
                .orElseThrow(FacturaNoEncontradaException::new);

        repositorioFactura.delete(factura);
    }

    @Override
    public FacturaDTO actualizar(Long id, FacturaDTO facturaDTO) {
        EntidadFacturas fac = repositorioFactura.findById(id)
                .orElseThrow(FacturaNoEncontradaException::new);

        fac.setNumero_factura(facturaDTO.numero_factura());
        fac.setFechaEmision(facturaDTO.fechaEmision());
        fac.setFechaVencimiento(facturaDTO.fechaVencimiento());
        fac.setTotal(facturaDTO.total());
        fac.setEstado(facturaDTO.estado());

        EntidadFacturas actualizado = repositorioFactura.save(fac);

        return facturaMapper.toDTO(actualizado);
    }

    @Override
    public FacturaDTO buscarPorId(Long id) {
        return repositorioFactura.findById(id).map(facturaMapper::toDTO).orElseThrow(FacturaNoEncontradaException::new);
    }

    @Override
    public List<FacturaDTO> buscarTodos() {
        return repositorioFactura.findAll().stream()
                .map(facturaMapper::toDTO)
                .toList();
    }
}
