package org.restaurante.msplazoleta.domain.usecase;

import org.restaurante.msplazoleta.application.dto.client.response.UserResponseDto;
import org.restaurante.msplazoleta.domain.exception.InvalidRoleException;
import org.restaurante.msplazoleta.domain.api.IRestaurantServicePort;
import org.restaurante.msplazoleta.domain.exception.RestaurantNotFoundException;
import org.restaurante.msplazoleta.domain.model.RestaurantEmployeeModel;
import org.restaurante.msplazoleta.domain.model.RestaurantModel;
import org.restaurante.msplazoleta.domain.spi.IRestaurantPersistencePort;
import org.restaurante.msplazoleta.infrastructure.client.IUserClient;

import java.util.List;

import static org.restaurante.msplazoleta.infrastructure.configuration.Constants.EMPLOYEE_ROLE_ID;
import static org.restaurante.msplazoleta.infrastructure.configuration.Constants.OWNER_ROLE_ID;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserClient userClient;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserClient userClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userClient = userClient;
    }

    @Override
    public void saveRestaurant(RestaurantModel restaurant) {

            UserResponseDto userDto = userClient.getUser(restaurant.getOwnerId());

            if(!userDto.getRoleId().equals(OWNER_ROLE_ID)) {
                throw new InvalidRoleException("The user is not owner");
            }
            restaurantPersistencePort.saveRestaurant(restaurant);

    }

    @Override
    public RestaurantModel getRestaurant(Long id) {
      return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants(Integer page, Integer pageSize) {
        return restaurantPersistencePort.getAllRestaurants(page, pageSize);
    }

    @Override
    public void saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {
        UserResponseDto userDto = userClient.getEmployee(restaurantEmployeeModel.getEmployeeEmail());
        RestaurantModel restaurantModel = restaurantPersistencePort.getRestaurant(restaurantEmployeeModel.getRestaurantId());

        if(!userDto.getRoleId().equals(EMPLOYEE_ROLE_ID)) {
            throw new InvalidRoleException("The user is not employee");
        }

        if(restaurantModel == null) {
            throw new RestaurantNotFoundException("The restaurant doesn't exist");
        }

        restaurantEmployeeModel.setEmployeeId(userDto.getId());

        restaurantPersistencePort.saveRestaurantEmployee(restaurantEmployeeModel);
    }

    @Override
    public RestaurantEmployeeModel getRestaurantEmployee(Long employeeId) {

        return restaurantPersistencePort.getRestaurantEmployee(employeeId);
    }
}
