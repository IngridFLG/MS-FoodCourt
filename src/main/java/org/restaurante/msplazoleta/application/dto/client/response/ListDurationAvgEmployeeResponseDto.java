package org.restaurante.msplazoleta.application.dto.client.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListDurationAvgEmployeeResponseDto {

    Long employeeId;

    String avg;

    List<ListTraceabilityResponseDto> orders;
}
