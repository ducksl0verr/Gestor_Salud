package com.GrupoProga3.Gestor_Salud.entity.Gastos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GastosDTO {

    private Long idGastos;

    @NotNull(message = "La fecha no puede ser nula")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    @Digits(integer = 12, fraction = 2, message = "El monto debe tener máximo 12 enteros y 2 decimales")
    private BigDecimal monto;

    @NotNull(message = "El id del tipo de gasto no puede ser nulo")
    @Positive(message = "El id del tipo de gasto debe ser un valor positivo")
    private Long idTipoGasto;

    @NotNull(message = "El id del método de pago no puede ser nulo")
    @Positive(message = "El id del método de pago debe ser un valor positivo")
    private Long idMetodoPago;

    @NotNull(message = "El id del proveedor no puede ser nulo")
    @Positive(message = "El id del proveedor debe ser un valor positivo")
    private Long idProvedor;

    @Size(max = 100, message = "El comprobante no puede superar los 100 caracteres")
    private String comprobante;

    @Size(max = 500, message = "Las observaciones no pueden superar los 500 caracteres")
    private String observaciones;
}
