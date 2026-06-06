package com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Gastos.Enums.TIPO_GASTO;
import com.GrupoProga3.Gestor_Salud.Metodos_de_Pago.METODOS_PAGO;

import java.time.LocalDate;

public record GastoRespuesta(Long id,
                             LocalDate fecha,
                             String descripcion,
                             Double monto,
                             TIPO_GASTO tipoGasto,
                             String nombreProveedor,
                             METODOS_PAGO metodoPago,
                             String observaciones) {
}
