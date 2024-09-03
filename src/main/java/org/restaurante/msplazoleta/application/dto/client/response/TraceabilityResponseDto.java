package org.restaurante.msplazoleta.application.dto.client.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TraceabilityResponseDto {

    private String state;

    private Long orderId;

    private Date changeStateOrder;
}
