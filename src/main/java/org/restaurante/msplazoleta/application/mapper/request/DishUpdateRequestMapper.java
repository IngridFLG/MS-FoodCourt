package org.restaurante.msplazoleta.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.application.dto.request.DishUpdateRequestDto;
import org.restaurante.msplazoleta.domain.model.DishModel;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishUpdateRequestMapper {

    DishModel toDishModel(DishUpdateRequestDto dishUpdateRequestDto);
}

