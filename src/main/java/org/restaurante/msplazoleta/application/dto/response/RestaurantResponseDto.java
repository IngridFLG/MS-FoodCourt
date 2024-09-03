package org.restaurante.msplazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseDto {

    private String name;

    private Integer nit;

    private String address;

    private String phone;

    private String urlLogo;

    private Long ownerId;
}
