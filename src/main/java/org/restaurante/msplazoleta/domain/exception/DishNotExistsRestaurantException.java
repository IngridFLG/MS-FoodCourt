package org.restaurante.msplazoleta.domain.exception;

public class DishNotExistsRestaurantException extends RuntimeException {
    public DishNotExistsRestaurantException(String msg) {
        super(msg);
    }
}
