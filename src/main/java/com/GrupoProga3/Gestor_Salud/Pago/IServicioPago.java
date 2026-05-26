package com.GrupoProga3.Gestor_Salud.Pago;

import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface IServicioPago {

    PagoDTO guardar (PagoDTO pagoDTO);

    void borrar (Long id);

    PagoDTO buscarPorId(Long id);

    PagoDTO actualizar(Long id, PagoDTO pagoDTO);

    List<PagoDTO>buscarTodos();
}
