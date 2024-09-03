package org.restaurante.msplazoleta.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.response.RestaurantEmployeeResponseDto;
import org.restaurante.msplazoleta.domain.model.RestaurantEmployeeModel;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEmployeeResponseMapper {

    RestaurantEmployeeResponseDto toRestaurantEmployeeResponseDto(RestaurantEmployeeModel restaurantEmployeeModel);
}
