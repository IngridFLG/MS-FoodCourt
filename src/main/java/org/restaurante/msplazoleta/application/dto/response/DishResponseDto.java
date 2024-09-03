package org.restaurante.msplazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishResponseDto {

    private String name;

    private Integer price;

    private String description;

    private String urlImage;

    private String category;

    private Boolean isEnable;

    private Long restaurantId;
}
