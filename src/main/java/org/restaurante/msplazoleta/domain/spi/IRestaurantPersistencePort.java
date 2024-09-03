package org.restaurante.msplazoleta.domain.spi;

import org.restaurante.msplazoleta.domain.model.RestaurantEmployeeModel;
import org.restaurante.msplazoleta.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {

    void saveRestaurant(RestaurantModel restaurant);

    RestaurantModel getRestaurant(Long id);

    List<RestaurantModel> getAllRestaurants(Integer page, Integer pageSize);

    void saveRestaurantEmployee(RestaurantEmployeeModel restaurant);

    RestaurantEmployeeModel getRestaurantEmployee(Long employeeId);
}
