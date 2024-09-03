package org.restaurante.msplazoleta.domain.exception;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(String msg) {
        super(msg);
    }
}
