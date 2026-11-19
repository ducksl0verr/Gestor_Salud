package com.GrupoProga3.Gestor_Salud.features.Gastos.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.Gastos.Enums.TIPO_GASTO;
import com.GrupoProga3.Gestor_Salud.features.Metodos_de_Pago.METODOS_PAGO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// La fecha no forma parte del alta: el servidor la fija con LocalDate.now().
public record GastoNuevo(@NotBlank (message = "Necesita una descripción")
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
