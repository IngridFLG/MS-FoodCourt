package org.restaurante.msplazoleta.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.request.RestaurantRequestDto;
import org.restaurante.msplazoleta.domain.model.RestaurantModel;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestMapper {

    RestaurantModel toRestaurantModel(RestaurantRequestDto restaurantRequestDto);


}
