package org.restaurante.msplazoleta.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.domain.model.OrderDishModel;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.OrderDishEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDishEntityMapper {

    List<OrderDishModel> toModelList(List<OrderDishEntity> orderDishEntities);

    List<OrderDishEntity> toEntityList(List<OrderDishModel> orderDishModels);
}
