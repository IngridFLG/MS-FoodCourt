package org.restaurante.msplazoleta.domain.model;

import java.util.List;

import org.restaurante.msplazoleta.domain.model.enums.StateEnum;

public class OrderModel {

    private Long id;

    private Long restaurantId;

    private Long clientId;

    private Long employeeId;

    private Integer pin;

    private List<OrderDishModel> orderDishes;

    private StateEnum state;

    public OrderModel() {
    }

    public OrderModel(Long id, Long restaurantId, Long clientId, Long employeeId, Integer pin, List<OrderDishModel> orderDishes, StateEnum state) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.pin = pin;
        this.orderDishes = orderDishes;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public List<OrderDishModel> getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(List<OrderDishModel> orderDishes) {
        this.orderDishes = orderDishes;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

}
