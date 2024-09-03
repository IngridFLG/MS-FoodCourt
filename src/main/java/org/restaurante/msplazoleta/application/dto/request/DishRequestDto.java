package org.restaurante.msplazoleta.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequestDto {

    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(message = "The price is required")
    @Min(value = 1, message = "The price must be greater than 0")
    private Integer price;

    @NotBlank(message = "The description is required")
    private String description;

    @NotBlank(message = "The urlImage is required")
    private String urlImage;

    @NotBlank(message = "The category is required")
    private String category;

    @NotNull(message = "The restaurantId is required")
    private Long restaurantId;
}
