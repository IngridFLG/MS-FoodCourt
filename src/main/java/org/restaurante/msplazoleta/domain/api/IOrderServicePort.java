package org.restaurante.msplazoleta.domain.api;
import org.restaurante.msplazoleta.application.dto.client.response.ListDurationAvgEmployeeResponseDto;
import org.restaurante.msplazoleta.application.dto.client.response.ListTraceabilityDurationResponseDto;
import org.restaurante.msplazoleta.application.dto.client.request.TraceabilityRequestDto;
import org.restaurante.msplazoleta.application.dto.client.response.TraceabilityResponseDto;
import org.restaurante.msplazoleta.domain.model.OrderModel;

import java.util.List;

public interface IOrderServicePort {

    void addOrder(OrderModel order);

    List<OrderModel> getOrdersByState(String state, Integer page, Integer pageSize ,Long userId);

    void assignToOrder(Long userId, Long orderId);

    OrderModel getOrderById(Long orderId, Long userId);

    void orderReady(Long userId, Long orderId);

    void orderDelivered(Long userId, Long orderId, Integer pin);

    void orderCancel(Long userId, Long orderId);

    List<TraceabilityResponseDto> getAllTraceability(Long userId, Long orderId);

    List<ListTraceabilityDurationResponseDto> getOrderStartAndEnd(Long restaurantId, Long userId);

    List<ListDurationAvgEmployeeResponseDto> getEmployeeAverage(Long restaurantId, Long userId);
}
