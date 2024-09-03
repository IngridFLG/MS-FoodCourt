package org.restaurante.msplazoleta.infrastructure.jpa.repository;

import org.restaurante.msplazoleta.infrastructure.jpa.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity, Long> {
    List<OrderDishEntity> findOrderDishEntityByOrderId(Long orderId);
}
