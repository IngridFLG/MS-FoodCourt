package org.restaurante.msplazoleta.application.handler.interfaces;

import org.restaurante.msplazoleta.application.dto.request.RestaurantEmployeeRequestDto;
import org.restaurante.msplazoleta.application.dto.request.RestaurantRequestDto;
import org.restaurante.msplazoleta.application.dto.response.RestaurantEmployeeResponseDto;
import org.restaurante.msplazoleta.application.dto.response.RestaurantListResponseDto;
import org.restaurante.msplazoleta.application.dto.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);

    RestaurantResponseDto getRestaurant(Long id);

    List<RestaurantListResponseDto> getRestaurants(Integer page, Integer pageSize);

    void saveRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);

    RestaurantEmployeeResponseDto getRestaurantEmployee(Long employeeId);

}
