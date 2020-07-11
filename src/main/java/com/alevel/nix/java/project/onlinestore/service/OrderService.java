package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.DeliveryAddress;
import com.alevel.nix.java.project.onlinestore.entity.Order;
import com.alevel.nix.java.project.onlinestore.entity.User;
import com.alevel.nix.java.project.onlinestore.entity.enums.OrderStatus;
import com.alevel.nix.java.project.onlinestore.entity.request.OrderRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.OrderResponse;
import com.alevel.nix.java.project.onlinestore.exception.OrderNotFoundException;
import com.alevel.nix.java.project.onlinestore.exception.OrderStatusChangeException;
import com.alevel.nix.java.project.onlinestore.exception.UserNotFoundException;
import com.alevel.nix.java.project.onlinestore.repository.OrderRepository;
import com.alevel.nix.java.project.onlinestore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService implements OrderOperations {

    private final OrderRepository orderRepository;


    private final UserRepository userRepository;


    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;

        this.userRepository = userRepository;
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        return new OrderResponse(order);
    }

    @Override
    public OrderResponse getOrderByUserIdAndOrderId(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        return new OrderResponse(order);
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<OrderResponse> responses = new ArrayList<>();
        for (Order order : orderRepository.findAllByUserId(userId)) {
            responses.add(new OrderResponse(order));
        }
        return responses ;
    }

    @Override
    public OrderResponse createOrderForUser(Long userId, OrderRequest orderRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setCityName(orderRequest.getCity());
        deliveryAddress.setStreetName(orderRequest.getStreet());
        deliveryAddress.setHouseNumber(orderRequest.getNumberOfHouse());
        deliveryAddress.setApartmentNumber(orderRequest.getNumberOfApartment());
        Order order = new Order(deliveryAddress, user);
        user.getOrders().add(order);
        orderRepository.save(order);
        return new OrderResponse(order);
    }

    @Override
    public void changeOrderStatus(Long userId, Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        if (!order.getOrderStatus().equals(OrderStatus.IN_THE_PROCESS)){
            throw new OrderStatusChangeException();
        }
        order.setOrderStatus(status);
        orderRepository.save(order);
    }


}