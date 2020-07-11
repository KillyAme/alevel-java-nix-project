package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.Order;
import com.alevel.nix.java.project.onlinestore.entity.enums.OrderStatus;
import com.alevel.nix.java.project.onlinestore.entity.request.OrderRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.OrderResponse;

import java.util.List;

public interface OrderOperations {

    OrderResponse getOrderById(Long orderId);

    OrderResponse getOrderByUserIdAndOrderId(Long userId, Long orderId);

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse createOrderForUser(Long id, OrderRequest orderRequest);

    void changeOrderStatus(Long userId, Long orderId, OrderStatus status);
}
