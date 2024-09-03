package org.restaurante.msplazoleta.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    @NotNull
    private Long restaurantId;

    @NotNull(message = "The order dish is required.")
    @Size(min = 1, message = "The order dish must be greater than 0.")
    @Valid
    @JsonProperty("orderDishes")
    private List<OrderDishRequestDto> orderDishRequestDto;
}
