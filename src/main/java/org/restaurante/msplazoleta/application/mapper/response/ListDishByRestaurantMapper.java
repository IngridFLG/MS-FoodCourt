package org.restaurante.msplazoleta.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.response.ListDishByRestaurantResponseDto;
import org.restaurante.msplazoleta.domain.model.DishModel;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ListDishByRestaurantMapper {

    List<ListDishByRestaurantResponseDto> toListDishByRestaurantResponseDto(List<DishModel> dishModel);
}
