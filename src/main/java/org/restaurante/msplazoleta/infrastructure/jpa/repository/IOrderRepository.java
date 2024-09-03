package org.restaurante.msplazoleta.infrastructure.jpa.repository;

import org.restaurante.msplazoleta.domain.model.OrderModel;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByClientIdAndStateIn(Long clientId, List<String> states);

    Page<OrderEntity> findByStateAndRestaurantId(String state, Long restaurantId, Pageable pageable);
}
