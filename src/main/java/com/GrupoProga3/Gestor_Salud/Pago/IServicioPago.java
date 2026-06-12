package com.GrupoProga3.Gestor_Salud.Pago;

import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoDTO;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoNuevo;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoRespuesta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface IServicioPago {

    PagoRespuesta guardar (PagoNuevo pagoNuevo);

    void borrar (Long id);

    PagoRespuesta buscarPorId(Long id);

    PagoRespuesta actualizar(Long id, PagoDTO pagoDTO);

    List<PagoRespuesta>buscarTodos();
}
