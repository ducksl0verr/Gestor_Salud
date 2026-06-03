package com.GrupoProga3.Gestor_Salud.Pago;

import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoDTO;
import com.GrupoProga3.Gestor_Salud.Pago.EntidadPago;
import com.GrupoProga3.Gestor_Salud.Pago.IServicioPago;
import com.GrupoProga3.Gestor_Salud.Pago.Mappers.PagoMapper;
import com.GrupoProga3.Gestor_Salud.Pago.RepositorioPago;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.math.BigDecimal;

@Service
@AllArgsConstructor

public class ServicioPago implements IServicioPago {
    private final RepositorioPago repositorioPago;
    private final PagoMapper pagoMapper;



    @Override

    public PagoDTO guardar(PagoDTO pagoDTO) {
        EntidadPago entidad = pagoMapper.toEntity(pagoDTO);
        EntidadPago guardado = repositorioPago.save(entidad);
        return pagoMapper.toDto(guardado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        if (!repositorioPago.existsById(id)) {
            throw new NoSuchElementException("Pago no encontrado con id: " + id);
        }
        repositorioPago.deleteById(id);
    }

    @Override
    @Transactional
    public PagoDTO buscarPorId(Long id) {
        return repositorioPago.findById(id)
                .map(pagoMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Pago no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public PagoDTO actualizar(Long id, PagoDTO pagoDTO) {
        EntidadPago entidad = repositorioPago.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pago no encontrado con id: " + id));

        BigDecimal montoDto = pagoDTO.monto();
        if (montoDto != null) entidad.setMonto(montoDto);

        if (pagoDTO.fecha() != null) entidad.setFecha(pagoDTO.fecha());

        EntidadPago actualizado = repositorioPago.save(entidad);
        return pagoMapper.toDto(actualizado);
    }

    @Override
    public List<PagoDTO> buscarTodos() {
        return repositorioPago.findAll().stream()
                .map(pagoMapper::toDto)
                .toList();
    }
}