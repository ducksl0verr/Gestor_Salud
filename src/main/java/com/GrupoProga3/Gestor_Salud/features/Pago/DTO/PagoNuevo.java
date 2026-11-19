package com.GrupoProga3.Gestor_Salud.features.Pago.DTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

// La fecha no forma parte del alta: el servidor la fija con LocalDateTime.now().
public record PagoNuevo(@NotNull @Positive @Digits(integer = 10, fraction = 2)
                        BigDecimal monto,
                        @NotEmpty(message = "El pago debe tener al menos un paciente asociado.")
                        List<Long> idsPacientes,
                        @NotEmpty(message = "Debe seleccionar al menos un turno para pagar.")
                        List<Long> idsTurnos){}
