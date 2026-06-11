package com.GrupoProga3.Gestor_Salud.Pago.excepciones;

public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String message) {
        super(message);
    }
}
