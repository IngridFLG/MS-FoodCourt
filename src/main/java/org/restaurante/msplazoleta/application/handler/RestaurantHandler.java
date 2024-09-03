package org.restaurante.msplazoleta.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.application.dto.request.RestaurantEmployeeRequestDto;
import org.restaurante.msplazoleta.application.dto.request.RestaurantRequestDto;
import org.restaurante.msplazoleta.application.dto.response.RestaurantEmployeeResponseDto;
import org.restaurante.msplazoleta.application.dto.response.RestaurantListResponseDto;
import org.restaurante.msplazoleta.application.dto.response.RestaurantResponseDto;
import org.restaurante.msplazoleta.application.handler.interfaces.IRestaurantHandler;
import org.restaurante.msplazoleta.application.mapper.request.RestaurantEmployeeRequestMapper;
import org.restaurante.msplazoleta.application.mapper.request.RestaurantRequestMapper;
import org.restaurante.msplazoleta.application.mapper.response.RestaurantEmployeeResponseMapper;
import org.restaurante.msplazoleta.application.mapper.response.RestaurantListResponseMapper;
import org.restaurante.msplazoleta.application.mapper.response.RestaurantResponseMapper;
import org.restaurante.msplazoleta.domain.api.IRestaurantServicePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantResponseMapper restaurantResponseMapper;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final RestaurantListResponseMapper restaurantListResponseMapper;
    private final RestaurantEmployeeRequestMapper restaurantEmployeeRequestMapper;
    private final RestaurantEmployeeResponseMapper restaurantEmployeeResponseMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurantModel(restaurantRequestDto));
    }

    @Override
    public RestaurantResponseDto getRestaurant(Long id) {
      return restaurantResponseMapper.toRestaurantResponseDto(restaurantServicePort.getRestaurant(id));
    }

    @Override
    public List<RestaurantListResponseDto> getRestaurants(Integer page, Integer pageSize) {
        return restaurantListResponseMapper.toRestaurantListRequestDto(restaurantServicePort.getAllRestaurants(page, pageSize));
    }

    @Override
    public void saveRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        restaurantServicePort.saveRestaurantEmployee(restaurantEmployeeRequestMapper.toRestaurantEmployeeModel(restaurantEmployeeRequestDto));
    }

    @Override
    public RestaurantEmployeeResponseDto getRestaurantEmployee(Long employeeId) {
        return restaurantEmployeeResponseMapper.toRestaurantEmployeeResponseDto(restaurantServicePort.getRestaurantEmployee(employeeId));
    }
}
