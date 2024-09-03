package org.restaurante.msplazoleta.infrastructure.configuration.exception;

public class JwtException extends RuntimeException{
    public  JwtException(String msg) {
        super(msg);
    }
}
