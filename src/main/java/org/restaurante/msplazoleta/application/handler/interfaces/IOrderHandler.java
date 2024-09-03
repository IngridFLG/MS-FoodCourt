package org.restaurante.msplazoleta.application.handler.interfaces;

import org.restaurante.msplazoleta.application.dto.client.response.ListDurationAvgEmployeeResponseDto;
import org.restaurante.msplazoleta.application.dto.client.response.ListTraceabilityDurationResponseDto;
import org.restaurante.msplazoleta.application.dto.client.request.TraceabilityRequestDto;
import org.restaurante.msplazoleta.application.dto.client.response.TraceabilityResponseDto;
import org.restaurante.msplazoleta.application.dto.request.OrderRequestDto;
import org.restaurante.msplazoleta.application.dto.response.ListOrderByStateResponseDto;
import org.restaurante.msplazoleta.application.dto.response.OrderResponseDto;

import java.util.List;

public interface IOrderHandler {

    void addOrder(OrderRequestDto orderRequestDto, Long userId);

    List<ListOrderByStateResponseDto> getOrderByState(String state, Integer page, Integer pageSize, Long userId);

    OrderResponseDto getOrderById(Long orderId, Long userId);

    void assignToOrder(Long userId, Long orderId);

    void orderReady(Long userId, Long orderId);

    void orderDelivered(Long userId, Long orderId, Integer pin);

    void orderCancel(Long userId, Long orderId);

    List<TraceabilityResponseDto> getAllTraceability(Long userId, Long orderId);

    List<ListTraceabilityDurationResponseDto> getOrderStartAndEnd(Long restaurantId, Long userId);

    List<ListDurationAvgEmployeeResponseDto> getEmployeeAverage(Long restaurantId, Long userId);
}
