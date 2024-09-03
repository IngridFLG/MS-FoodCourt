package org.restaurante.msplazoleta.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishRequestDto {

    @NotNull(message = "The dish id is required")
    private Long dishId;

    @NotNull(message = "The quantity is required")
    @Positive(message = "The quantity must be greater than 0")
    private Integer quantity;
}
