package org.restaurante.msplazoleta.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.application.dto.request.DishRequestDto;
import org.restaurante.msplazoleta.application.dto.request.DishUpdateRequestDto;
import org.restaurante.msplazoleta.application.dto.response.DishResponseDto;
import org.restaurante.msplazoleta.application.dto.response.ListDishByRestaurantResponseDto;
import org.restaurante.msplazoleta.application.handler.interfaces.IDishHandler;
import org.restaurante.msplazoleta.application.mapper.request.DishRequestMapper;
import org.restaurante.msplazoleta.application.mapper.request.DishUpdateRequestMapper;
import org.restaurante.msplazoleta.application.mapper.response.DishResponseMapper;
import org.restaurante.msplazoleta.application.mapper.response.ListDishByRestaurantMapper;
import org.restaurante.msplazoleta.domain.api.IDishServicePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final DishRequestMapper dishRequestMapper;
    private final DishResponseMapper dishResponseMapper;
    private final DishUpdateRequestMapper dishUpdateRequestMapper;
    private final ListDishByRestaurantMapper listDishByRestaurantMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto, Long userId) {
        dishServicePort.saveDish(dishRequestMapper.toDishModel(dishRequestDto), userId);
    }

    @Override
    public void updateDish(DishUpdateRequestDto dishUpdateRequestDto, Long userId, Long dishId) {
        dishServicePort.updateDish(dishUpdateRequestMapper.toDishModel(dishUpdateRequestDto), userId, dishId);
    }

    @Override
    public DishResponseDto getDish(Long id) {
        return dishResponseMapper.toDishResponseDto(dishServicePort.getDish(id));
    }

    @Override
    public void updateStateDish(Long userId, Long dishId) {
        dishServicePort.updateStateDish(dishId, userId);
    }

    @Override
    public List<ListDishByRestaurantResponseDto> getListDishByRestaurant(Long restaurantId, Integer page, Integer pageSize, String category) {
        return listDishByRestaurantMapper.toListDishByRestaurantResponseDto(dishServicePort.getDishByRestaurant(restaurantId, page, pageSize, category));
    }
}
