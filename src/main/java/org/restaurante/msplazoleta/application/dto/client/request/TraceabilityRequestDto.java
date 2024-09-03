package org.restaurante.msplazoleta.application.dto.client.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TraceabilityRequestDto {

    private String state;

    private Long orderId;

    private Long restaurantId;

    private Long employeeId;

    private Date changeStateOrder;
}
