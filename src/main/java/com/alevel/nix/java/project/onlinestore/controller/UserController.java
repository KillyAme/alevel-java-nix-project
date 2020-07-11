package com.alevel.nix.java.project.onlinestore.controller;

import com.alevel.nix.java.project.onlinestore.entity.enums.OrderStatus;
import com.alevel.nix.java.project.onlinestore.entity.request.OrderRequest;
import com.alevel.nix.java.project.onlinestore.entity.request.UserRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.BasketResponse;
import com.alevel.nix.java.project.onlinestore.entity.response.OrderResponse;
import com.alevel.nix.java.project.onlinestore.entity.response.UserResponse;
import com.alevel.nix.java.project.onlinestore.service.BasketOperations;
import com.alevel.nix.java.project.onlinestore.service.OrderOperations;
import com.alevel.nix.java.project.onlinestore.service.UserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserOperations userOperations;
    private final BasketOperations basketOperations;
    private final OrderOperations orderOperations;

    public UserController(UserOperations userOperations, BasketOperations basketOperations, OrderOperations orderOperations) {
        this.userOperations = userOperations;
        this.basketOperations = basketOperations;
        this.orderOperations = orderOperations;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse userResponse = userOperations.createUser(request);
        return ResponseEntity
                .created(URI.create("/users/" + userResponse.getId()))
                .body(userResponse);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userOperations.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        userOperations.updateUser(id, request);
    }

    @GetMapping("/{id}/basket")
    public BasketResponse getUserBasket(@PathVariable Long id) {
        return basketOperations.getBasketByUserId(id);
    }

    @DeleteMapping("/{id}/basket")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void clearUserBasket(@PathVariable Long id) {
        basketOperations.clearBasketByUser(id);
    }

    @PutMapping("/{id}/basket/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProductInUserBasket(@PathVariable Long id, @RequestBody Long productId) {
        basketOperations.addProductInBasketByUserIdAndProductId(id, productId);
    }

    @DeleteMapping("/{id}/basket/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductOfUserBasket(@PathVariable Long id, @RequestBody Long productId) {
        basketOperations.deleteProductOfBasketByUserIdAndProductId(id, productId);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public OrderResponse getOrder(@PathVariable Long userId, @PathVariable Long orderId) {

        return orderOperations.getOrderByUserIdAndOrderId(userId, orderId);
    }

    @GetMapping("/{userId}/orders")
    public List<OrderResponse> getOrders(@PathVariable Long userId) {
        return orderOperations.getOrdersByUserId(userId);
    }

    @PostMapping("/{userId}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderResponse> createUserOrder(@PathVariable Long userId, @RequestBody OrderRequest request) {
        OrderResponse orderResponse = orderOperations.createOrderForUser(userId, request);
        return ResponseEntity
                .created(URI.create("/users/" + userId + "/orders/" + orderResponse.getId()))
                .body(orderResponse);
    }

    @PutMapping("/{userId}/orders/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeOrderStatus(@PathVariable Long userId, @PathVariable Long orderId, @RequestBody OrderStatus status) {
        orderOperations.changeOrderStatus(userId, orderId, status);
    }

}
