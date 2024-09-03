package org.restaurante.msplazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListOrderByStateResponseDto {

    private Long id;

    private Long restaurantId;

    private Long clientId;

    private Long employeeId;

    private List<ListOrderDishResponseDto> orderDishes;

}
