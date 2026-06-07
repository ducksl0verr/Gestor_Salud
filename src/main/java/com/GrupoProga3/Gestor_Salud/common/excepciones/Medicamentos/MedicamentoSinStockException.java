package com.GrupoProga3.Gestor_Salud.common.excepciones.Medicamentos;

public class MedicamentoSinStockException extends RuntimeException {
    public MedicamentoSinStockException(String message) {
        super(message);
    }
}
