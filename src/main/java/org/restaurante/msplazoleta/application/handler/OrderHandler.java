package org.restaurante.msplazoleta.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.application.dto.client.response.ListDurationAvgEmployeeResponseDto;
import org.restaurante.msplazoleta.application.dto.client.response.ListTraceabilityDurationResponseDto;
import org.restaurante.msplazoleta.application.dto.client.request.TraceabilityRequestDto;
import org.restaurante.msplazoleta.application.dto.client.response.TraceabilityResponseDto;
import org.restaurante.msplazoleta.application.dto.request.OrderRequestDto;
import org.restaurante.msplazoleta.application.dto.response.ListOrderByStateResponseDto;
import org.restaurante.msplazoleta.application.dto.response.OrderResponseDto;
import org.restaurante.msplazoleta.application.handler.interfaces.IOrderHandler;
import org.restaurante.msplazoleta.application.mapper.request.OrderRequestMapper;
import org.restaurante.msplazoleta.application.mapper.response.ListOrderByStateResponseMapper;
import org.restaurante.msplazoleta.application.mapper.response.OrderResponseMapper;
import org.restaurante.msplazoleta.domain.api.IOrderServicePort;
import org.restaurante.msplazoleta.domain.model.OrderModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final OrderRequestMapper orderRequestMapper;
    private final ListOrderByStateResponseMapper listOrderByStateResponseMapper;
    private final OrderResponseMapper orderResponseMapper;

    @Override
    public void addOrder(OrderRequestDto orderRequestDto, Long userId) {
        OrderModel orderModel = orderRequestMapper.toModel(orderRequestDto);
        orderModel.setClientId(userId);
        orderServicePort.addOrder(orderModel);
    }

    @Override
    public List<ListOrderByStateResponseDto> getOrderByState(String state, Integer page, Integer pageSize, Long userId) {
        return listOrderByStateResponseMapper.toListOrderByStateResponseDto(orderServicePort.getOrdersByState(state, page, pageSize, userId));
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId, Long userId) {
        return orderResponseMapper.toOrderResponseDto(orderServicePort.getOrderById(orderId, userId));
    }

    @Override
    public void assignToOrder(Long userId, Long orderId) {
        orderServicePort.assignToOrder(userId, orderId);
    }

    @Override
    public void orderReady(Long userId, Long orderId) {
        orderServicePort.orderReady(userId, orderId);
    }

    @Override
    public void orderDelivered(Long userId, Long orderId, Integer pin) {
        orderServicePort.orderDelivered(userId, orderId, pin);
    }

    @Override
    public void orderCancel(Long userId, Long orderId) {
        orderServicePort.orderCancel(userId, orderId);
    }

    @Override
    public List<TraceabilityResponseDto> getAllTraceability(Long userId, Long orderId) {
        return orderServicePort.getAllTraceability(userId, orderId);
    }

    @Override
    public List<ListTraceabilityDurationResponseDto> getOrderStartAndEnd(Long restaurantId, Long userId) {
        return orderServicePort.getOrderStartAndEnd(restaurantId, userId);
    }

    @Override
    public List<ListDurationAvgEmployeeResponseDto> getEmployeeAverage(Long restaurantId, Long userId) {
        return orderServicePort.getEmployeeAverage(restaurantId, userId);
    }
}
