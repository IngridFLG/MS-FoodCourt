package org.restaurante.msplazoleta.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequestDto {

    @NotBlank(message = "The name is required")
    @Pattern(regexp = ".*\\D.*", message = "The name cannot be purely numeric")
    private String name;

    @NotNull(message = "The nit is required")
    private Integer nit;

    @NotBlank(message = "The address is required")
    private String address;

    @NotBlank(message = "The phone is required")
    @Pattern(regexp = "^\\+?57\\s?(3[0-2]|7[0-1])\\d{8}$", message = "The number phone is not valid")
    private String phone;

    @NotBlank(message = "The urlLogo is required")
    private String urlLogo;

    @NotNull(message = "The ownerId is required")
    private Long ownerId;
}
