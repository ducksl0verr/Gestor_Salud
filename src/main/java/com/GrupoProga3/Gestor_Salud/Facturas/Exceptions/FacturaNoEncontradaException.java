package com.GrupoProga3.Gestor_Salud.Facturas.Exceptions;

public class FacturaNoEncontradaException extends RuntimeException {
    public FacturaNoEncontradaException() {
        super("Factura no encontrada!");
    }
}
