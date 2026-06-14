package com.GrupoProga3.Gestor_Salud.features.Pago.DTO;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record PagoDTO (
        BigDecimal monto,
        LocalDateTime fecha)
{}
