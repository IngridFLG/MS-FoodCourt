package org.restaurante.msplazoleta.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.domain.model.RestaurantModel;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.RestaurantEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {

    RestaurantEntity toRestaurantEntity(RestaurantModel restaurantModel);

    RestaurantModel toRestaurantModel(RestaurantEntity restaurantEntity);

    List<RestaurantModel> toRestaurantModelList(List<RestaurantEntity> restaurantEntities);
}
