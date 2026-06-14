package com.GrupoProga3.Gestor_Salud.features.Facturas.Exceptions;

public class FacturaNoEncontradaException extends RuntimeException {
    public FacturaNoEncontradaException() {
        super("Factura no encontrada!");
    }
}
