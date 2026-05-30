package com.GrupoProga3.Gestor_Salud.DetalleFacturas.Dominio;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record DetalleFacturaDTO(@NotBlank(message = "El concepto no puede estar vacío")
                                String concepto,
                                @NotNull(message = "El importe no puede ser nulo")
                                @PositiveOrZero(message = "El total debe ser mayor o igual a 0")
                                BigDecimal importe,
                                @NotNull(message = "La cantidad no puede ser nula")
                                @Min(value = 1, message = "La cantidad debe ser al menos 1")
                                Long cantidad){}
