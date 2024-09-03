package org.restaurante.msplazoleta.domain.usecase;

import org.restaurante.msplazoleta.application.dto.client.response.ListDurationAvgEmployeeResponseDto;
import org.restaurante.msplazoleta.application.dto.client.response.ListTraceabilityDurationResponseDto;
import org.restaurante.msplazoleta.application.dto.client.request.TraceabilityRequestDto;
import org.restaurante.msplazoleta.application.dto.client.request.TwilioRequestDto;
import org.restaurante.msplazoleta.application.dto.client.response.TraceabilityResponseDto;
import org.restaurante.msplazoleta.application.dto.client.response.UserResponseDto;
import org.restaurante.msplazoleta.domain.api.IOrderServicePort;
import org.restaurante.msplazoleta.domain.exception.*;
import org.restaurante.msplazoleta.domain.model.*;
import org.restaurante.msplazoleta.domain.model.enums.StateEnum;
import org.restaurante.msplazoleta.domain.spi.IDishPersistencePort;
import org.restaurante.msplazoleta.domain.spi.IOrderPersistencePort;
import org.restaurante.msplazoleta.domain.spi.IRestaurantPersistencePort;
import org.restaurante.msplazoleta.infrastructure.client.IMessageClient;
import org.restaurante.msplazoleta.infrastructure.client.ITraceabilityClient;
import org.restaurante.msplazoleta.infrastructure.client.IUserClient;

import java.util.*;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IMessageClient messageClient;
    private final IUserClient userClient;
    private final ITraceabilityClient traceabilityClient;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, IMessageClient messageClient, IUserClient userClient, ITraceabilityClient traceabilityClient) {
        this.orderPersistencePort = orderPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.messageClient = messageClient;
        this.userClient = userClient;
        this.traceabilityClient = traceabilityClient;
    }

    @Override
    public void addOrder(OrderModel order) {
        List<String> states = Arrays.asList("IN_PREPARATION", "PENDING", "READY");
        List<OrderModel> orderPending = orderPersistencePort.getOrdersByClientAndState(order.getClientId(), states);

        if(!orderPending.isEmpty()) {
            throw  new OrdersPendingException("The user " + order.getClientId() + " has orders pending");
        }

        for(OrderDishModel orderDishModel: order.getOrderDishes()) {
            DishModel dishModel = dishPersistencePort.getDish(orderDishModel.getDishId());
            if(!dishModel.getRestaurantId().equals(order.getRestaurantId())) {
                throw new DishNotExistsRestaurantException("The dish " + dishModel.getName() + " not exists in the restaurant");
            }
        }
        order.setState(StateEnum.PENDING);
        OrderModel orderModel = orderPersistencePort.addOrder(order);

        saveTraceability(orderModel.getState().name(), orderModel.getId(), orderModel.getRestaurantId(), orderModel.getEmployeeId());
    }

    @Override
    public List<OrderModel> getOrdersByState(String state, Integer page, Integer pageSize, Long userId) {
        RestaurantEmployeeModel employee = restaurantPersistencePort.getRestaurantEmployee(userId);

        StateEnum stateEnum = StateEnum.valueOf(state.toUpperCase());

        return orderPersistencePort.getOrdersByState(stateEnum, page, pageSize, employee.getRestaurantId());
    }


    @Override
    public void assignToOrder(Long userId, Long orderId){
        OrderModel orderModel = userIsNotEmployeeRestaurant(userId, orderId);

        if(!orderModel.getState().equals(StateEnum.PENDING)) {
            throw new OrderStateException("The order state is not pending");
        }

        if(orderModel.getEmployeeId() != null) {
            throw new UserInvalid("The order a ready has an employee");
        }

        orderModel.setEmployeeId(userId);
        orderModel.setState(StateEnum.IN_PREPARATION);
        orderModel.setOrderDishes(new ArrayList<>());
        orderPersistencePort.putOrder(orderModel);

        saveTraceability(orderModel.getState().name(), orderModel.getId(), orderModel.getRestaurantId(), orderModel.getEmployeeId());
    }

    @Override
    public OrderModel getOrderById(Long orderId, Long userId) {
        userIsNotEmployeeRestaurant(userId, orderId);
        return orderPersistencePort.getOrderById(orderId);

    }

    @Override
    public void orderReady(Long userId, Long orderId) {
        OrderModel orderModel = userIsNotEmployeeRestaurant(userId, orderId);
        if(!orderModel.getState().equals(StateEnum.IN_PREPARATION)) {
            throw new OrderStateException("The order state is not in preparation");
        }
        Integer pin = generatePin();

        UserResponseDto userDto = userClient.getClient(orderModel.getClientId());

        TwilioRequestDto twilioDto = new TwilioRequestDto();
        twilioDto.setMessage(pin);
        twilioDto.setPhoneNumber(userDto.getPhone());

        messageClient.sendMessage(twilioDto);

        orderModel.setPin(pin);
        orderModel.setState(StateEnum.READY);
        orderModel.setOrderDishes(new ArrayList<>());
        orderPersistencePort.putOrder(orderModel);

        saveTraceability(orderModel.getState().name(), orderModel.getId(), orderModel.getRestaurantId(), orderModel.getEmployeeId());
    }

    @Override
    public void orderDelivered(Long userId, Long orderId, Integer pin) {
        OrderModel orderModel = userIsNotEmployeeRestaurant(userId, orderId);
        if(!orderModel.getState().equals(StateEnum.READY)) {
            throw new OrderStateException("The order state is not ready");
        }

        if(!pin.equals(orderModel.getPin())) {
            throw new PinIncorrectException("The pin typed is incorrect");
        }

        orderModel.setState(StateEnum.DELIVERED);
        orderModel.setOrderDishes(new ArrayList<>());
        orderPersistencePort.putOrder(orderModel);

        saveTraceability(orderModel.getState().name(), orderModel.getId(), orderModel.getRestaurantId(), orderModel.getEmployeeId());
    }

    @Override
    public void orderCancel(Long userId, Long orderId) {
        OrderModel orderModel = clientIsNotOrder(userId, orderId);

        if(!orderModel.getState().equals(StateEnum.PENDING)) {
            throw new OrderStateException("Sorry, your order is already in preparation and cannot be cancelled.");
        }

        orderModel.setState(StateEnum.CANCEL);
        orderPersistencePort.putOrder(orderModel);

        saveTraceability(orderModel.getState().name(), orderModel.getId(), orderModel.getRestaurantId(), orderModel.getEmployeeId());
    }

    @Override
    public List<TraceabilityResponseDto> getAllTraceability(Long userId, Long orderId) {
        clientIsNotOrder(userId, orderId);

        return traceabilityClient.getAllTraceability(orderId);
    }

    @Override
    public List<ListTraceabilityDurationResponseDto> getOrderStartAndEnd(Long restaurantId, Long userId) {
        RestaurantModel restaurantModel = restaurantPersistencePort.getRestaurant(restaurantId);
        if(!restaurantModel.getOwnerId().equals(userId)) {
            throw new InvalidRoleException("The user is not owner of the restaurant");
        }
        return traceabilityClient.getOrderStartAndEnd(restaurantId);
    }

    @Override
    public List<ListDurationAvgEmployeeResponseDto> getEmployeeAverage(Long restaurantId, Long userId) {
        RestaurantModel restaurantModel = restaurantPersistencePort.getRestaurant(restaurantId);
        if(!restaurantModel.getOwnerId().equals(userId)) {
            throw new InvalidRoleException("The user is not owner of the restaurant");
        }

        return traceabilityClient.getEmployeeAverage(restaurantId);
    }

    private OrderModel userIsNotEmployeeRestaurant(Long userId, Long orderId) {
        RestaurantEmployeeModel restaurantEmployeeModel = restaurantPersistencePort.getRestaurantEmployee(userId);
        OrderModel orderModel = orderPersistencePort.getOrderById(orderId);
        if(!restaurantEmployeeModel.getRestaurantId().equals(orderModel.getRestaurantId())){
            throw new UserIsNotEmployeeRestaurantException("The employee is not associated with the restaurant");
        }

        if(orderModel.getEmployeeId() != null && !orderModel.getEmployeeId().equals(userId) ) {
            throw new UserInvalid("The employee is not assign to order");
        }
        return orderModel;
    }

    private OrderModel clientIsNotOrder(Long userId, Long orderId) {
        OrderModel orderModel = orderPersistencePort.getOrderById(orderId);

        if(!orderModel.getClientId().equals(userId)) {
            throw new UserInvalid("The order does not belong to the customer");
        }

        return orderModel;
    }

    private Integer generatePin() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    private void saveTraceability(String state, Long orderId, Long restaurantId, Long employeeId) {

        TraceabilityRequestDto traceabilityDto = new TraceabilityRequestDto();
        traceabilityDto.setState(state);
        traceabilityDto.setOrderId(orderId);
        traceabilityDto.setChangeStateOrder(new Date());
        traceabilityDto.setRestaurantId(restaurantId);
        traceabilityDto.setEmployeeId(employeeId);

        traceabilityClient.saveTraceability(traceabilityDto);
    }

}
