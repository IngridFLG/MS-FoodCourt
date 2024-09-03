package org.restaurante.msplazoleta.domain.exception;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String msg) {
        super(msg);
    }
}
