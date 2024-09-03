package org.restaurante.msplazoleta.domain.spi;

import org.restaurante.msplazoleta.domain.model.OrderModel;
import org.restaurante.msplazoleta.domain.model.enums.StateEnum;

import java.util.List;

public interface IOrderPersistencePort {

    OrderModel addOrder(OrderModel order);

    List<OrderModel> getOrdersByState(StateEnum state, Integer page, Integer pageSize ,Long restaurantId);

    void putOrder(OrderModel order);

    List<OrderModel> getOrdersByClientAndState(Long clientId, List<String> state);

    OrderModel getOrderById(Long orderId);
}
