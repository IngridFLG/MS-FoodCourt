package org.restaurante.msplazoleta.domain.model;

public class OrderDishModel {

    private Long id;

    private Long dishId;

    private Integer quantity;

    private Long orderId;

    public OrderDishModel() {
    }

    public OrderDishModel(Long id, Long dishId, Integer quantity, Long orderId) {
        this.id = id;
        this.dishId = dishId;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
