package org.restaurante.msplazoleta.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.request.OrderRequestDto;
import org.restaurante.msplazoleta.domain.model.OrderModel;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRequestMapper {

    @Mapping(source = "orderDishRequestDto", target = "orderDishes")
    @Mapping(source = "restaurantId", target = "restaurantId")
    OrderModel toModel(OrderRequestDto dto);

    @Mapping(source = "orderDishes", target = "orderDishRequestDto")
    @Mapping(source = "restaurantId", target = "restaurantId")
    OrderRequestDto toDto(OrderModel model);
}
