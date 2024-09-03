package org.restaurante.msplazoleta.domain.model;

public class RestaurantEmployeeModel {

    private Long id;

    private String employeeEmail;

    private Long employeeId;

    private Long restaurantId;

    public RestaurantEmployeeModel() {
    }

    public RestaurantEmployeeModel(Long id, String employeeEmail, Long employeeId, Long restaurantId) {
        this.id = id;
        this.employeeEmail = employeeEmail;
        this.employeeId = employeeId;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}