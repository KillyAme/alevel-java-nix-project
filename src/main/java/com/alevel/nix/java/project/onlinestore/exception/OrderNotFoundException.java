package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends OnlineStoreException {
    public OrderNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Order with id: " + id + " not found.");
    }
}
