package org.restaurante.msplazoleta.domain.exception;

public class OrdersPendingException extends RuntimeException{

    public OrdersPendingException(String msg) {
        super(msg);
    }
}
