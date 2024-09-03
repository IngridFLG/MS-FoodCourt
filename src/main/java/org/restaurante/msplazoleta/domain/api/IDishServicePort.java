package org.restaurante.msplazoleta.domain.api;

import org.restaurante.msplazoleta.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {

    void saveDish(DishModel dishModel, Long userId);

    void updateDish(DishModel dishModel, Long userId, Long dishId);

    DishModel getDish(Long dishId);

    void updateStateDish(Long dishId, Long userId);

    List<DishModel> getDishByRestaurant(Long restaurantId, Integer page, Integer pageSize, String category);

}
