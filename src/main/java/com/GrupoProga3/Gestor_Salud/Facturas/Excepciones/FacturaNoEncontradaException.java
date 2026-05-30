package com.GrupoProga3.Gestor_Salud.Facturas.Excepciones;

public class FacturaNoEncontradaException extends RuntimeException {
    public FacturaNoEncontradaException() {
        super("Factura no encontrada!");
    }
}
