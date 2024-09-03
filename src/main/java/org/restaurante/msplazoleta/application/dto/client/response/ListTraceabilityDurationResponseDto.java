package org.restaurante.msplazoleta.application.dto.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListTraceabilityDurationResponseDto {

    private Long orderId;

    private String duration;

    private String state;
}
