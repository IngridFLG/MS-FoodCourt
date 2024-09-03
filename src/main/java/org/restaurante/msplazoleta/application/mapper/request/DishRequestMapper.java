package org.restaurante.msplazoleta.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.request.DishRequestDto;
import org.restaurante.msplazoleta.domain.model.DishModel;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishRequestMapper {

    DishModel toDishModel(DishRequestDto dishRequestDto);
}
