package org.restaurante.msplazoleta.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.response.RestaurantResponseDto;
import org.restaurante.msplazoleta.domain.model.RestaurantModel;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantResponseMapper {

    RestaurantResponseDto toRestaurantResponseDto(RestaurantModel restaurantModel);
}
