package org.restaurante.msplazoleta.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.response.ListOrderByStateResponseDto;
import org.restaurante.msplazoleta.domain.model.OrderModel;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ListOrderByStateResponseMapper {

    List<ListOrderByStateResponseDto> toListOrderByStateResponseDto(List<OrderModel> orderModels);
}
