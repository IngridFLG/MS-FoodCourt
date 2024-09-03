package org.restaurante.msplazoleta.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.request.RestaurantEmployeeRequestDto;
import org.restaurante.msplazoleta.domain.model.RestaurantEmployeeModel;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEmployeeRequestMapper {

    RestaurantEmployeeModel toRestaurantEmployeeModel(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);
}
