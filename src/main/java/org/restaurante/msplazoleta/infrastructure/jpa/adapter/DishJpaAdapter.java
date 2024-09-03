package org.restaurante.msplazoleta.infrastructure.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.domain.model.DishModel;
import org.restaurante.msplazoleta.domain.model.enums.CategoryEnum;
import org.restaurante.msplazoleta.domain.spi.IDishPersistencePort;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.DishEntity;
import org.restaurante.msplazoleta.infrastructure.jpa.exception.NotFoundException;
import org.restaurante.msplazoleta.infrastructure.jpa.mapper.DishEntityMapper;
import org.restaurante.msplazoleta.infrastructure.jpa.repository.IDishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;


    @Override
    public void saveDish(DishModel dish) {
        dishRepository.save(dishEntityMapper.toDishEntity(dish));
    }


    @Override
    public DishModel getDish(Long dishId) {

        Optional<DishEntity> dish = dishRepository.findById(dishId);

        if (dish.isPresent()) {

            return dishEntityMapper.toDishModel(dish.get());
        }

        throw new NotFoundException("No dish found with id " + dishId);
    }

    @Override
    public List<DishModel> getDishByRestaurant(Long restaurantId, Integer page, Integer pageSize, CategoryEnum category) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<DishEntity> dishEntities = dishRepository.findByRestaurantIdAndCategory(restaurantId, category.name(), pageable);

        return dishEntityMapper.toDishModelList(dishEntities.getContent());
    }

    @Override
    public List<DishModel> getDishByRestaurant(Long restaurantId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<DishEntity> dishEntities = dishRepository.findByRestaurantId(restaurantId, pageable);

        return dishEntityMapper.toDishModelList(dishEntities.getContent());
    }

}
