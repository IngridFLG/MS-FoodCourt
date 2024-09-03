package org.restaurante.msplazoleta.domain.exception;

public class UserIsNotEmployeeRestaurantException extends RuntimeException {

    public UserIsNotEmployeeRestaurantException(String msg) {
        super(msg);
    }
}
