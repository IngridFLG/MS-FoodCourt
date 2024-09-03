package org.restaurante.msplazoleta.domain.api;

import org.restaurante.msplazoleta.domain.model.RestaurantEmployeeModel;
import org.restaurante.msplazoleta.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurant);

    RestaurantModel getRestaurant(Long id);

    List<RestaurantModel> getAllRestaurants(Integer page, Integer pageSize);

    void saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel);

    RestaurantEmployeeModel getRestaurantEmployee(Long employeeId);
}
