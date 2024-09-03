package org.restaurante.msplazoleta.domain.usecase;

import org.restaurante.msplazoleta.domain.api.IDishServicePort;
import org.restaurante.msplazoleta.domain.exception.DishNotFoundException;
import org.restaurante.msplazoleta.domain.exception.InvalidCategoryException;
import org.restaurante.msplazoleta.domain.exception.UserIsNotOwnerRestaurantException;
import org.restaurante.msplazoleta.domain.model.DishModel;
import org.restaurante.msplazoleta.domain.model.RestaurantModel;
import org.restaurante.msplazoleta.domain.model.enums.CategoryEnum;
import org.restaurante.msplazoleta.domain.spi.IDishPersistencePort;
import org.restaurante.msplazoleta.domain.spi.IRestaurantPersistencePort;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IRestaurantPersistencePort restaurantPersistencePort, IDishPersistencePort dishPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void saveDish(DishModel dishModel, Long userId) {

        userIsNotOwnerRestaurant(dishModel, userId);

        dishModel.setIsEnable(true);
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public void updateDish(DishModel dishModel, Long userId, Long dishId) {

        DishModel dish = dishPersistencePort.getDish(dishId);

        userIsNotOwnerRestaurant(dish, userId);

        dish.setPrice(dishModel.getPrice());
        dish.setDescription(dishModel.getDescription());

        dishPersistencePort.saveDish(dish);
    }

    @Override
    public DishModel getDish(Long dishId) {
        DishModel dishModel = dishPersistencePort.getDish(dishId);
        if(dishModel == null) {
            throw new DishNotFoundException("The dish with id" + dishId + "not exist");
        }
        return dishModel;
    }

    @Override
    public void updateStateDish(Long dishId, Long userId) {
        DishModel dishModel = dishPersistencePort.getDish(dishId);
        userIsNotOwnerRestaurant(dishModel, userId);
        dishModel.setIsEnable(!dishModel.getIsEnable());
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public List<DishModel> getDishByRestaurant(Long restaurantId, Integer page, Integer pageSize, String category) {


        if (category==null || category.isEmpty()) {
           return dishPersistencePort.getDishByRestaurant(restaurantId, page, pageSize);
        }

        try {
            CategoryEnum categoryEnum = CategoryEnum.valueOf(category.toUpperCase());
            return dishPersistencePort.getDishByRestaurant(restaurantId, page, pageSize, categoryEnum);

        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException("The provided category is not valid: " + category);
        }

    }

    private void userIsNotOwnerRestaurant(DishModel dishModel, Long userId) {
        RestaurantModel restaurantModel = restaurantPersistencePort.getRestaurant(dishModel.getRestaurantId());
        if (!restaurantModel.getOwnerId().equals(userId)) {
            throw new UserIsNotOwnerRestaurantException("User is not owner of restaurant");
        }
    }
}
