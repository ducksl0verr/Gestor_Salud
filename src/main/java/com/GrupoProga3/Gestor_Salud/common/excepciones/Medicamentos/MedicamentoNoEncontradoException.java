package com.GrupoProga3.Gestor_Salud.common.excepciones.Medicamentos;

public class MedicamentoNoEncontradoException extends RuntimeException {
    public MedicamentoNoEncontradoException(String message) {
        super(message);
    }
}
