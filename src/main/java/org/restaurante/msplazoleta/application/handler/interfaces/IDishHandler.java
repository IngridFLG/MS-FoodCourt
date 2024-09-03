package org.restaurante.msplazoleta.application.handler.interfaces;

import org.restaurante.msplazoleta.application.dto.request.DishRequestDto;
import org.restaurante.msplazoleta.application.dto.request.DishUpdateRequestDto;
import org.restaurante.msplazoleta.application.dto.response.DishResponseDto;
import org.restaurante.msplazoleta.application.dto.response.ListDishByRestaurantResponseDto;

import java.util.List;


public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto, Long userId);

    void updateDish(DishUpdateRequestDto dishUpdateRequestDto, Long userId, Long dishId);

    DishResponseDto getDish(Long id);

    void updateStateDish(Long userId, Long dishId);

    List<ListDishByRestaurantResponseDto> getListDishByRestaurant(Long restaurantId, Integer page, Integer pageSize, String category);
}
