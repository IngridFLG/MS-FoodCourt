package org.restaurante.msplazoleta.infrastructure.jpa.repository;

import org.restaurante.msplazoleta.infrastructure.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    Optional<RestaurantEntity> findByNit(Integer nit);

    Optional<RestaurantEntity> findByPhone(String phone);

}
