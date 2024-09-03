package org.restaurante.msplazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListOrderDishResponseDto {

    private Long id;

    private Long dishId;

    private Integer quantity;

    private Long orderId;
}
