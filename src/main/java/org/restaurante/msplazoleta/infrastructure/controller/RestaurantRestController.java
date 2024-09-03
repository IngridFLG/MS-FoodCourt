package org.restaurante.msplazoleta.infrastructure.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.application.dto.request.RestaurantEmployeeRequestDto;
import org.restaurante.msplazoleta.application.dto.request.RestaurantRequestDto;
import org.restaurante.msplazoleta.application.dto.response.RestaurantListResponseDto;
import org.restaurante.msplazoleta.application.dto.response.RestaurantResponseDto;
import org.restaurante.msplazoleta.application.handler.interfaces.IRestaurantHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @PostMapping("/create")
    public ResponseEntity<Void> saveRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurant(@PathVariable("id") Long id, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantHandler.getRestaurant(id));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantListResponseDto>> getAllRestaurants(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return  ResponseEntity.status(HttpStatus.OK).body(restaurantHandler.getRestaurants(page, pageSize));

    }

    @PostMapping("/create/employee")
    public ResponseEntity<Void> saveRestaurantEmployee(@Valid @RequestBody RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        restaurantHandler.saveRestaurantEmployee(restaurantEmployeeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
