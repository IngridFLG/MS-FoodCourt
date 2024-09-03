package org.restaurante.msplazoleta.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.domain.model.RestaurantEmployeeModel;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.RestaurantEmployeeEntity;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEmployeeEntityMapper {

    RestaurantEmployeeEntity toRestaurantEmployeeEntity(RestaurantEmployeeModel restaurantEmployeeModel);

    RestaurantEmployeeModel toRestaurantEmployeeModel(RestaurantEmployeeEntity restaurantEmployeeEntity);
}
