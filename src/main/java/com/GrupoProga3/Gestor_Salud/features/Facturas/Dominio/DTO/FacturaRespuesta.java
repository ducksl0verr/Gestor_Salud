package com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.ENUMS.EstadoFactura;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FacturaRespuesta(Long id,
                               String numero_factura,
                               LocalDate fechaEmision,
                               LocalDate fechaVencimiento,
                               BigDecimal total,
                               EstadoFactura estadoFactura)
{}
