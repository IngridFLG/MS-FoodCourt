package org.restaurante.msplazoleta.infrastructure.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.domain.model.RestaurantEmployeeModel;
import org.restaurante.msplazoleta.domain.model.RestaurantModel;
import org.restaurante.msplazoleta.domain.spi.IRestaurantPersistencePort;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.RestaurantEmployeeEntity;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.RestaurantEntity;
import org.restaurante.msplazoleta.infrastructure.jpa.exception.ExistsPhoneException;
import org.restaurante.msplazoleta.infrastructure.jpa.exception.ExistsRestaurantException;
import org.restaurante.msplazoleta.infrastructure.jpa.exception.NotFoundException;
import org.restaurante.msplazoleta.infrastructure.jpa.mapper.RestaurantEmployeeEntityMapper;
import org.restaurante.msplazoleta.infrastructure.jpa.mapper.RestaurantEntityMapper;
import org.restaurante.msplazoleta.infrastructure.jpa.repository.IRestaurantEmployeeRepository;
import org.restaurante.msplazoleta.infrastructure.jpa.repository.IRestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final RestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;

    @Override
    public void saveRestaurant(RestaurantModel restaurant) {
        if (restaurantRepository.findByNit(restaurant.getNit()).isPresent()) {
            throw new ExistsRestaurantException();
        }
        if (restaurantRepository.findByPhone(restaurant.getPhone()).isPresent()) {
            throw new ExistsPhoneException();
        }

        restaurantRepository.save(restaurantEntityMapper.toRestaurantEntity(restaurant));
    }

    @Override
    public RestaurantModel getRestaurant(Long id) {

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(id);

       if (restaurant.isPresent()) {

        return restaurantEntityMapper.toRestaurantModel(restaurant.get());
       }

       throw new NotFoundException("No restaurant found with id " + id);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants(Integer page, Integer pageSize) {

        PageRequest sortedByName = PageRequest.of(
                page, pageSize,
                Sort.by("name").ascending()
        );
        Page<RestaurantEntity> restaurantEntities = restaurantRepository.findAll(sortedByName);

        return restaurantEntityMapper.toRestaurantModelList(restaurantEntities.getContent());
    }

    @Override
    public void saveRestaurantEmployee(RestaurantEmployeeModel restaurant) {
        restaurantEmployeeRepository.save(restaurantEmployeeEntityMapper.toRestaurantEmployeeEntity(restaurant));
    }

    @Override
    public RestaurantEmployeeModel getRestaurantEmployee(Long employeeId) {
        Optional<RestaurantEmployeeEntity> employee = restaurantEmployeeRepository.findByEmployeeId(employeeId);

        if (employee.isPresent()) {
            return  restaurantEmployeeEntityMapper.toRestaurantEmployeeModel(employee.get());
        }

        throw new NotFoundException("No employee found with id " + employeeId);
    }


}
