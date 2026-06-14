package com.GrupoProga3.Gestor_Salud.Pago.DTO;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteNuevo;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PagoNuevo(@NotNull @Positive @Digits(integer = 10, fraction = 2)
                        BigDecimal monto,
                        @NotNull
                        LocalDateTime fecha,
                        List<Long> idsPacientes){}
