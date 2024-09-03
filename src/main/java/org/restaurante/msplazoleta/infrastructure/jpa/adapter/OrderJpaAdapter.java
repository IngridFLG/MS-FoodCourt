package org.restaurante.msplazoleta.infrastructure.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.domain.model.OrderDishModel;
import org.restaurante.msplazoleta.domain.model.OrderModel;
import org.restaurante.msplazoleta.domain.model.enums.StateEnum;
import org.restaurante.msplazoleta.domain.spi.IOrderPersistencePort;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.OrderDishEntity;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.OrderEntity;
import org.restaurante.msplazoleta.infrastructure.jpa.exception.NotFoundException;
import org.restaurante.msplazoleta.infrastructure.jpa.mapper.OrderDishEntityMapper;
import org.restaurante.msplazoleta.infrastructure.jpa.mapper.OrderEntityMapper;
import org.restaurante.msplazoleta.infrastructure.jpa.repository.IOrderDishRepository;
import org.restaurante.msplazoleta.infrastructure.jpa.repository.IOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderDishRepository orderDishRepository;
    private final IOrderRepository orderRepository;
    private final OrderDishEntityMapper orderDishEntityMapper;
    private final OrderEntityMapper orderEntityMapper;


    @Override
    public OrderModel addOrder(OrderModel order) {
        List<OrderDishEntity> orderDishEntities = orderDishEntityMapper.toEntityList(order.getOrderDishes());
        OrderEntity orderEntity = orderEntityMapper.toOrderEntity(order);
        
        OrderEntity orderDB = orderRepository.save(orderEntity);
        for(OrderDishEntity orderDishes: orderDishEntities) {
            orderDishes.setOrderId(orderDB.getId());
            orderDishRepository.save(orderDishes);
        }

        return orderEntityMapper.toOrderModel(orderDB);
    }

    @Override
    public List<OrderModel> getOrdersByState(StateEnum state, Integer page, Integer pageSize, Long restaurantId) {
        // Crear una instancia de Pageable con el número de página, tamaño de página y ordenación (si es necesario)
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

        // Obtener las entidades Order con paginación
        Page<OrderEntity> orderPage = orderRepository.findByStateAndRestaurantId(state.name(), restaurantId, pageable);

        // Mapear las entidades Order a modelos
        List<OrderModel> orderModels = orderEntityMapper.toOrderModelList(orderPage.getContent());

        // Para cada OrderModel, obtener y mapear las entidades OrderDish a modelos, luego asignar al OrderModel correspondiente
        for (OrderModel orderModel : orderModels) {
            List<OrderDishModel> orderDishModels = orderDishEntityMapper.toModelList(orderDishRepository.findOrderDishEntityByOrderId(orderModel.getId()));
            orderModel.setOrderDishes(orderDishModels);
        }

        // Retornar la lista de OrderModels con sus respectivos OrderDishModels asignados
        return orderModels;
    }

    @Override
    public void putOrder(OrderModel order) {
        orderRepository.save(orderEntityMapper.toOrderEntity(order));
    }

    @Override
    public List<OrderModel> getOrdersByClientAndState(Long clientId, List<String> states) {
        return orderEntityMapper.toOrderModelList(orderRepository.findByClientIdAndStateIn(clientId, states));
    }

    @Override
    public OrderModel getOrderById(Long orderId) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isEmpty()) {
            throw new NotFoundException("The order with id: " + orderId + " not exist");
        }
        return orderEntityMapper.toOrderModel(orderEntity.get());
    }
}
