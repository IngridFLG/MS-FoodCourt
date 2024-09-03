package org.restaurante.msplazoleta.infrastructure.jpa.repository;

import org.restaurante.msplazoleta.infrastructure.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    Page<DishEntity> findByRestaurantId(Long restaurantId, Pageable pageable);

    Page<DishEntity> findByRestaurantIdAndCategory(Long restaurantId, String category, Pageable pageable);
}
