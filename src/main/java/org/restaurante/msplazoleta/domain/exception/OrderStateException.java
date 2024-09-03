package org.restaurante.msplazoleta.domain.exception;

public class OrderStateException extends RuntimeException {
    public OrderStateException(String msg) {
        super(msg);
    }
}
