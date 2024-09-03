package org.restaurante.msplazoleta.domain.spi;

import org.restaurante.msplazoleta.domain.model.DishModel;
import org.restaurante.msplazoleta.domain.model.enums.CategoryEnum;

import java.util.List;

public interface IDishPersistencePort {

    void saveDish(DishModel dish);

    DishModel getDish(Long dishId);

    List<DishModel> getDishByRestaurant(Long restaurantId, Integer page, Integer pageSize, CategoryEnum category);

    List<DishModel> getDishByRestaurant(Long restaurantId, Integer page, Integer pageSize);

}
