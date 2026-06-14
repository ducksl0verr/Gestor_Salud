package com.GrupoProga3.Gestor_Salud.features.DetalleFacturas.Excepciones;

public class DetalleFacturaNoEncontrado extends RuntimeException {
    public DetalleFacturaNoEncontrado() {
        super("Detalle de factura no encontrado");
    }
}
