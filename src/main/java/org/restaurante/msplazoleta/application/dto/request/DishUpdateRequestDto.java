package org.restaurante.msplazoleta.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishUpdateRequestDto {

    @NotNull(message = "The price is required")
    @Min(value = 1, message = "The price must be greater than 0")
    private String price;

    @NotBlank(message = "The description is required")
    private String description;
}
