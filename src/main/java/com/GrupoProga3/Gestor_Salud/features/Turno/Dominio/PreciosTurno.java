package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio;

import java.math.BigDecimal;

/**
 * Precio aplicado a los turnos que son una consulta sin tratamiento asociado.
 */
public final class PreciosTurno {

    public static final BigDecimal PRECIO_BASE_CONSULTA = new BigDecimal("10000");
    public static final String CONCEPTO_CONSULTA = "Consulta sin tratamiento";

    private PreciosTurno() {
    }
}
