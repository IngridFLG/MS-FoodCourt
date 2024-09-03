package org.restaurante.msplazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.restaurante.msplazoleta.domain.model.enums.StateEnum;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;

    private Long restaurantId;

    private Long clientId;

    private Long employeeId;

    private List<ListOrderDishResponseDto> orderDishes;

    private String state;
}
