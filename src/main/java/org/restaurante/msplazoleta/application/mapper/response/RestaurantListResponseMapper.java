package org.restaurante.msplazoleta.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.response.RestaurantListResponseDto;
import org.restaurante.msplazoleta.domain.model.RestaurantModel;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantListResponseMapper {

    List<RestaurantListResponseDto> toRestaurantListRequestDto(List<RestaurantModel> restaurantModel);
}
