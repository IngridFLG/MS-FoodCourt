package org.restaurante.msplazoleta.infrastructure.client;

import org.restaurante.msplazoleta.application.dto.client.response.ListDurationAvgEmployeeResponseDto;
import org.restaurante.msplazoleta.application.dto.client.response.ListTraceabilityDurationResponseDto;
import org.restaurante.msplazoleta.application.dto.client.request.TraceabilityRequestDto;
import org.restaurante.msplazoleta.application.dto.client.response.TraceabilityResponseDto;
import org.restaurante.msplazoleta.infrastructure.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "MS-Trazabilidad", url = "http://localhost:8060", configuration = FeignConfiguration.class)
public interface ITraceabilityClient {

    @PostMapping(value = "/traceability/save")
    void saveTraceability(@RequestBody TraceabilityRequestDto traceabilityDto);

    @GetMapping(value = "/traceability/{orderId}")
    List<TraceabilityResponseDto> getAllTraceability(@PathVariable("orderId") Long orderId);

    @GetMapping(value = "/traceability/order/startEnd/{restaurantId}")
    List<ListTraceabilityDurationResponseDto> getOrderStartAndEnd(@PathVariable("restaurantId") Long restaurantId);

    @GetMapping(value = "/traceability/order/average/{restaurantId}")
    List<ListDurationAvgEmployeeResponseDto> getEmployeeAverage(@PathVariable("restaurantId") Long restaurantId);
}
