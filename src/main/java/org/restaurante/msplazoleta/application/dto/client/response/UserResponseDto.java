package org.restaurante.msplazoleta.application.dto.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private String name;

    private String lastname;

    private Integer dni;

    private String email;

    private String phone;

    private Long roleId;
}
