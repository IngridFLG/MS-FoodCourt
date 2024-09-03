package org.restaurante.msplazoleta.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.domain.model.OrderModel;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.OrderEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

    @Mapping(source = "state", target = "state")
    OrderEntity toOrderEntity(OrderModel orderModel);

    OrderModel toOrderModel(OrderEntity orderEntity);

    List<OrderModel> toOrderModelList(List<OrderEntity> orderEntities);

}
