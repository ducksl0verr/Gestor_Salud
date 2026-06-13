package com.GrupoProga3.Gestor_Salud.common.excepciones;

import java.time.Instant;
import java.util.NoSuchElementException;

public class EntidadNoEncontradaException extends NoSuchElementException {

    private final String entidad;
    private final String campo;
    private final Long valor;
    private final Instant momento;

    public EntidadNoEncontradaException(String entidad, String campo, Long valor, String message) {
        super(message);
        this.entidad = entidad;
        this.campo = campo;
        this.valor = valor;
        this.momento = Instant.now();
    }
}
