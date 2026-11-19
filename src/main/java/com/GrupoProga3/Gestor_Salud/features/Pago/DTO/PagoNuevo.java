package com.GrupoProga3.Gestor_Salud.features.Pago.DTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PagoNuevo(@NotNull @Positive @Digits(integer = 10, fraction = 2)
                        BigDecimal monto,
                        LocalDateTime fecha,
                        @NotEmpty(message = "El pago debe tener al menos un paciente asociado.")
                        List<Long> idsPacientes,
                        @NotEmpty(message = "Debe seleccionar al menos un turno para pagar.")
                        List<Long> idsTurnos){}
