package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.enums.OrderStatus;
import com.alevel.nix.java.project.onlinestore.entity.request.OrderRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.OrderResponse;

import java.util.List;

public interface OrderOperations {

    OrderResponse getOrderByUserIdAndOrderId(Long userId, Long orderId);

    List<OrderResponse> getOrdersByUserId(Long userId);

    List<OrderResponse> getOrdersByUserIdAndStatus(Long userId, OrderStatus status);

    OrderResponse createOrderForUser(Long id, OrderRequest orderRequest);

    void changeOrderStatus(Long userId, Long orderId, OrderStatus status);
}
