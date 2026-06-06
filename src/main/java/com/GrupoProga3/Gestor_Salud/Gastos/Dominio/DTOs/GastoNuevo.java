package com.GrupoProga3.Gestor_Salud.Gastos.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Gastos.Enums.TIPO_GASTO;
import com.GrupoProga3.Gestor_Salud.Metodos_de_Pago.METODOS_PAGO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record GastoNuevo(@PastOrPresent
                         LocalDate fecha,
                         @NotBlank (message = "Necesita una descripción")
                         String descripcion,
                         @NotNull
                         Double monto,
                         @NotNull
                         TIPO_GASTO tipoGasto,
                         @NotNull
                         String nombreProveedor,
                         @NotNull
                         METODOS_PAGO metodoPago,
                         @NotBlank
                         String observaciones) {
}
