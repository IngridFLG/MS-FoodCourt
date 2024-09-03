package org.restaurante.msplazoleta.infrastructure.configuration;

import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.domain.api.IDishServicePort;
import org.restaurante.msplazoleta.domain.api.IOrderServicePort;
import org.restaurante.msplazoleta.domain.api.IRestaurantServicePort;
import org.restaurante.msplazoleta.domain.spi.IDishPersistencePort;
import org.restaurante.msplazoleta.domain.spi.IOrderPersistencePort;
import org.restaurante.msplazoleta.domain.spi.IRestaurantPersistencePort;
import org.restaurante.msplazoleta.domain.usecase.DishUseCase;
import org.restaurante.msplazoleta.domain.usecase.OrderUseCase;
import org.restaurante.msplazoleta.domain.usecase.RestaurantUseCase;
import org.restaurante.msplazoleta.infrastructure.client.IMessageClient;
import org.restaurante.msplazoleta.infrastructure.client.ITraceabilityClient;
import org.restaurante.msplazoleta.infrastructure.client.IUserClient;
import org.restaurante.msplazoleta.infrastructure.jpa.adapter.DishJpaAdapter;
import org.restaurante.msplazoleta.infrastructure.jpa.adapter.OrderJpaAdapter;
import org.restaurante.msplazoleta.infrastructure.jpa.adapter.RestaurantJpaAdapter;
import org.restaurante.msplazoleta.infrastructure.jpa.mapper.*;
import org.restaurante.msplazoleta.infrastructure.jpa.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    //restaurant
    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final RestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;

    // dish
    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

    // Clients
    private final IUserClient userClient;
    private final IMessageClient messageClient;
    private final ITraceabilityClient traceabilityClient;

    // Order
    private final OrderDishEntityMapper orderDishEntityMapper;
    private final OrderEntityMapper orderEntityMapper;
    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper, restaurantEmployeeRepository, restaurantEmployeeEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), userClient);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(restaurantPersistencePort(), dishPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderDishRepository, orderRepository, orderDishEntityMapper, orderEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(orderPersistencePort(), dishPersistencePort(), restaurantPersistencePort(), messageClient, userClient, traceabilityClient);
    }
}
