package org.restaurante.msplazoleta.infrastructure.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.application.dto.client.response.ListDurationAvgEmployeeResponseDto;
import org.restaurante.msplazoleta.application.dto.client.response.ListTraceabilityDurationResponseDto;
import org.restaurante.msplazoleta.application.dto.client.request.TraceabilityRequestDto;
import org.restaurante.msplazoleta.application.dto.client.response.TraceabilityResponseDto;
import org.restaurante.msplazoleta.application.dto.request.OrderRequestDto;
import org.restaurante.msplazoleta.application.dto.response.ListOrderByStateResponseDto;
import org.restaurante.msplazoleta.application.dto.response.OrderResponseDto;
import org.restaurante.msplazoleta.application.handler.interfaces.IOrderHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @PostMapping
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        orderHandler.addOrder(orderRequestDto, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<ListOrderByStateResponseDto>> getOrderByState(@PathVariable("state") String state, @RequestParam Integer page, @RequestParam Integer pageSize, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.status(HttpStatus.OK).body(orderHandler.getOrderByState(state, page, pageSize, userId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderByState(@PathVariable("orderId") Long orderId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.status(HttpStatus.OK).body(orderHandler.getOrderById(orderId, userId));
    }

    @PatchMapping("/assign/{orderId}")
    public ResponseEntity<Void> assignToOrder(@PathVariable("orderId") Long orderId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        orderHandler.assignToOrder(userId, orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/ready/{orderId}")
    public ResponseEntity<Void> orderReady(@PathVariable("orderId") Long orderId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        orderHandler.orderReady(userId, orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/delivered/{orderId}")
    public ResponseEntity<Void> orderDelivered(@PathVariable("orderId") Long orderId, @RequestParam Integer pin, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        orderHandler.orderDelivered(userId, orderId, pin);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<Void> orderCancel(@PathVariable("orderId") Long orderId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        orderHandler.orderCancel(userId, orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/traceability/{orderId}")
    public ResponseEntity<List<TraceabilityResponseDto>> getAllTraceability(@PathVariable("orderId") Long orderId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.status(HttpStatus.OK).body(orderHandler.getAllTraceability(userId, orderId));
    }

    @GetMapping("/traceability/startEnd/{restaurantId}")
    public ResponseEntity<List<ListTraceabilityDurationResponseDto>> getOrderStartAndEnd(@PathVariable("restaurantId")Long restaurantId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.status(HttpStatus.OK).body(orderHandler.getOrderStartAndEnd(restaurantId, userId));
    }

    @GetMapping("/traceability/average/{restaurantId}")
    public ResponseEntity<List<ListDurationAvgEmployeeResponseDto>> getEmployeeAverage(@PathVariable("restaurantId")Long restaurantId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.status(HttpStatus.OK).body(orderHandler.getEmployeeAverage(restaurantId, userId));
    }
}
