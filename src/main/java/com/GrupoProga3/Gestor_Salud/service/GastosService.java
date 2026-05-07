package com.GrupoProga3.Gestor_Salud.service;

import com.GrupoProga3.Gestor_Salud.entity.Gastos.Gastos;
import com.GrupoProga3.Gestor_Salud.entity.Gastos.GastosDTO;
import com.GrupoProga3.Gestor_Salud.entity.MetodosDePagos.MetodosDePagos;
import com.GrupoProga3.Gestor_Salud.entity.Proveedores.Proveedores;
import com.GrupoProga3.Gestor_Salud.entity.TipoGasto.TipoGasto;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.GastosRepository;
import com.GrupoProga3.Gestor_Salud.repository.MetodosDePagosRepository;
import com.GrupoProga3.Gestor_Salud.repository.ProveedoresRepository;
import com.GrupoProga3.Gestor_Salud.repository.TipoGastoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GastosService {

    private final GastosRepository gastosRepository;
    private final TipoGastoRepository tipoGastoRepository;
    private final MetodosDePagosRepository metodosDePagosRepository;
    private final ProveedoresRepository proveedoresRepository;

    public GastosService(GastosRepository gastosRepository,
                         TipoGastoRepository tipoGastoRepository,
                         MetodosDePagosRepository metodosDePagosRepository,
                         ProveedoresRepository proveedoresRepository) {
        this.gastosRepository = gastosRepository;
        this.tipoGastoRepository = tipoGastoRepository;
        this.metodosDePagosRepository = metodosDePagosRepository;
        this.proveedoresRepository = proveedoresRepository;
    }

    @Transactional(readOnly = true)
    public List<GastosDTO> getAll() {
        return gastosRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public GastosDTO getById(Long id) {
        return gastosRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Gasto no encontrado con id: " + id));
    }

    public GastosDTO create(GastosDTO dto) {
        Gastos gasto = new Gastos();
        mapDtoToEntity(dto, gasto);
        return toDTO(gastosRepository.save(gasto));
    }

    public GastosDTO update(Long id, GastosDTO dto) {
        Gastos gasto = gastosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gasto no encontrado con id: " + id));
        mapDtoToEntity(dto, gasto);
        return toDTO(gastosRepository.save(gasto));
    }

    public void delete(Long id) {
        if (!gastosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Gasto no encontrado con id: " + id);
        }
        gastosRepository.deleteById(id);
    }

    private void mapDtoToEntity(GastosDTO dto, Gastos gasto) {
        gasto.setFecha(dto.getFecha());
        gasto.setDescripcion(dto.getDescripcion());
        gasto.setMonto(dto.getMonto());
        gasto.setComprobante(dto.getComprobante());
        gasto.setObservaciones(dto.getObservaciones());

        TipoGasto tipoGasto = tipoGastoRepository.findById(dto.getIdTipoGasto())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de gasto no encontrado con id: " + dto.getIdTipoGasto()));
        gasto.setTipoGasto(tipoGasto);

        MetodosDePagos metodoPago = metodosDePagosRepository.findById(dto.getIdMetodoPago())
                .orElseThrow(() -> new ResourceNotFoundException("Método de pago no encontrado con id: " + dto.getIdMetodoPago()));
        gasto.setMetodoPago(metodoPago);

        Proveedores provedor = proveedoresRepository.findById(dto.getIdProvedor())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con id: " + dto.getIdProvedor()));
        gasto.setProvedor(provedor);
    }

    private GastosDTO toDTO(Gastos gasto) {
        GastosDTO dto = new GastosDTO();
        dto.setIdGastos(gasto.getIdGastos());
        dto.setFecha(gasto.getFecha());
        dto.setDescripcion(gasto.getDescripcion());
        dto.setMonto(gasto.getMonto());
        dto.setComprobante(gasto.getComprobante());
        dto.setObservaciones(gasto.getObservaciones());
        dto.setIdTipoGasto(gasto.getTipoGasto().getIdTipoGasto());
        dto.setIdMetodoPago(gasto.getMetodoPago().getIdMetodoDePago());
        dto.setIdProvedor(gasto.getProvedor().getIdProvedor());
        return dto;
    }
}
