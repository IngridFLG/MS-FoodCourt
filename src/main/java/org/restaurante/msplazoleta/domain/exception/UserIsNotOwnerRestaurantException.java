package org.restaurante.msplazoleta.domain.exception;

public class UserIsNotOwnerRestaurantException extends RuntimeException{
    public UserIsNotOwnerRestaurantException(String msg) {
        super(msg);
    }
}
