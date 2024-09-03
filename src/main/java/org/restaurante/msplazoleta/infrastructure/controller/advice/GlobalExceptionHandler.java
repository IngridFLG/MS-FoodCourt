package org.restaurante.msplazoleta.infrastructure.controller.advice;

import org.restaurante.msplazoleta.application.dto.response.GenericResponseDto;
import org.restaurante.msplazoleta.domain.exception.*;
import org.restaurante.msplazoleta.infrastructure.configuration.exception.JwtException;
import org.restaurante.msplazoleta.infrastructure.jpa.exception.ExistsPhoneException;
import org.restaurante.msplazoleta.infrastructure.jpa.exception.ExistsRestaurantException;
import org.restaurante.msplazoleta.infrastructure.jpa.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<GenericResponseDto> handleException(Exception ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({ExistsRestaurantException.class})
    public ResponseEntity<GenericResponseDto> existsRestaurantException(ExistsRestaurantException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg("El restaurante ya está registrado");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({ExistsPhoneException.class})
    public ResponseEntity<GenericResponseDto> existsPhoneException(ExistsPhoneException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg("El teléfono del restaurante ya está registrado");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({JwtException.class})
    public ResponseEntity<GenericResponseDto> jwtException(JwtException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({InvalidRoleException.class})
    public ResponseEntity<GenericResponseDto> invalidRoleException(InvalidRoleException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<GenericResponseDto> notFoundException(NotFoundException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({UserIsNotOwnerRestaurantException.class})
    public ResponseEntity<GenericResponseDto> userIsNotOwnerRestaurantException(UserIsNotOwnerRestaurantException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler({InvalidCategoryException.class})
    public ResponseEntity<GenericResponseDto> invalidCategoryException(InvalidCategoryException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({DishNotFoundException.class})
    public ResponseEntity<GenericResponseDto> dishNotFoundException(DishNotFoundException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({OrdersPendingException.class})
    public ResponseEntity<GenericResponseDto> ordersPendingException(OrdersPendingException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({UserIsNotEmployeeRestaurantException.class})
    public ResponseEntity<GenericResponseDto> userIsNotEmployeeRestaurantException(UserIsNotEmployeeRestaurantException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({OrderStateException.class})
    public ResponseEntity<GenericResponseDto> orderStateException(OrderStateException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({PinIncorrectException.class})
    public ResponseEntity<GenericResponseDto> pinIncorrectException(PinIncorrectException ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({UserInvalid.class})
    public ResponseEntity<GenericResponseDto> userAssignInvalid(UserInvalid ex) {
        GenericResponseDto response = new GenericResponseDto();
        response.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
