package com.GrupoProga3.Gestor_Salud.DetalleFacturas.Excepciones;

public class DetalleFacturaNoEncontrado extends RuntimeException {
    public DetalleFacturaNoEncontrado() {
        super("Detalle de factura no encontrado");
    }
}
