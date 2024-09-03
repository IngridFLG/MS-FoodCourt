package org.restaurante.msplazoleta.domain.exception;

public class InvalidCategoryException extends RuntimeException{
    public InvalidCategoryException(String msg) {
        super(msg);
    }
}
