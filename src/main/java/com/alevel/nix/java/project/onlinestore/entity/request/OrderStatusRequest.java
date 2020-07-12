package com.alevel.nix.java.project.onlinestore.entity.request;

import com.alevel.nix.java.project.onlinestore.entity.enums.OrderStatus;

public class OrderStatusRequest {
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
