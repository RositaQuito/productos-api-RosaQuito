package com.ejemplo.productos.excepcion;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Map;

@RestControllerAdvice
public class ManejadorExcepciones {
    @ExceptionHandler(ProductoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> manejarNoEncontrado(ProductoNoEncontradoException ex) {
        return Map.of(
                "mensaje", ex.getMessage(),
                "codigoEstado", HttpStatus.NOT_FOUND.value()
        );
    }
    @ExceptionHandler(ProductoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> manejarInvalido(ProductoInvalidoException ex) {
        return Map.of(
                "mensaje", ex.getMessage(),
                "codigoEstado", HttpStatus.BAD_REQUEST.value()
        );
    }
}