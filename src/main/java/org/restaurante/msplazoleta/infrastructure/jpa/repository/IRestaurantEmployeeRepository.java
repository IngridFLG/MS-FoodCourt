package org.restaurante.msplazoleta.infrastructure.jpa.repository;

import org.restaurante.msplazoleta.infrastructure.jpa.entity.RestaurantEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantEmployeeRepository extends JpaRepository<RestaurantEmployeeEntity, Long> {

    Optional<RestaurantEmployeeEntity> findByEmployeeId(Long employeeId);
}
