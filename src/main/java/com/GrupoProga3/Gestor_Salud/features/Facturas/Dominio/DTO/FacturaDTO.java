package com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.DTO;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record FacturaDTO(@NotBlank(message = "El numero de factura no puede estar vacio")
                         String numero_factura,
                         @NotNull(message = "La fecha de emisión no puede ser nula")
                         @PastOrPresent(message = "La fecha de emisión no puede ser futura")
                         LocalDate fechaEmision,
                         @NotNull(message = "La fecha de vencimiento no puede ser nula")
                         @Future(message = "La fecha de vencimiento debe ser futura")
                         LocalDate fechaVencimiento,
                         @NotNull(message = "El total no puede ser nulo")
                         @PositiveOrZero(message = "El total debe ser mayor o igual a 0")
                         BigDecimal total,
                         @NotBlank(message = "El estado no puede estar vacío")
                         @Size(max = 50, message = "El estado no puede superar los 50 caracteres")
                         String estado
){}
