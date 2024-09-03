package org.restaurante.msplazoleta.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.restaurante.msplazoleta.domain.model.DishModel;
import org.restaurante.msplazoleta.infrastructure.jpa.entity.DishEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishEntityMapper {

    DishEntity toDishEntity(DishModel dishModel);

    DishModel toDishModel(DishEntity dishEntity);

    List<DishModel> toDishModelList(List<DishEntity> dishEntities);
}
