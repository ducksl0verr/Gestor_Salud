package com.GrupoProga3.Gestor_Salud.common.handlers;

import com.GrupoProga3.Gestor_Salud.common.excepciones.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadNoEncontradaException.class)
    public ResponseEntity<ErrorRespuesta> manejoEntidadNoEncontrada(EntidadNoEncontradaException ex,
                                                                    HttpServletRequest request) {
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RecursoOcupadoException.class)
    public ResponseEntity<ErrorRespuesta> manejoRecursoOcupado (RecursoOcupadoException ex,
                                                                HttpServletRequest request) {
        ErrorRespuesta error = new ErrorRespuesta(LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(FaltaDeRecursoException.class)
    public ResponseEntity<ErrorRespuesta> manejoFaltaRecurso (FaltaDeRecursoException ex,
                                                              HttpServletRequest request) {
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<ErrorRespuesta> manejoReglaNegocio (ReglaNegocioException ex,
                                                              HttpServletRequest request) {
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejoValidaciones (MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(),
                                                    error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
