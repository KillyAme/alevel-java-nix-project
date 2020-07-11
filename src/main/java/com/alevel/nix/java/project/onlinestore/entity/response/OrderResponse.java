package com.alevel.nix.java.project.onlinestore.entity.response;

import com.alevel.nix.java.project.onlinestore.entity.Order;
import com.alevel.nix.java.project.onlinestore.entity.Product;
import com.alevel.nix.java.project.onlinestore.entity.enums.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private Long id;
    private List<ProductInBasketResponse> products = new ArrayList<>();
    private Long orderPrice;
    private OrderStatus status;
    private Instant timestamp;

    public OrderResponse(Order order) {
        this.id = order.getId();
        for (Product product : order.getProductList()) {
            products.add(new ProductInBasketResponse(product));
        }
        this.orderPrice = order.getOrderPrice();
        this.status = order.getOrderStatus();
        this.timestamp = order.getTimestamp();
    }

    public OrderResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductInBasketResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInBasketResponse> products) {
        this.products = products;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
