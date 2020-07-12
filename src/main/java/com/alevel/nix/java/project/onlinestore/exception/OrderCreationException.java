package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class OrderCreationException extends OnlineStoreException {
    public OrderCreationException() {
        super(HttpStatus.CONFLICT, "Basket is empty");
    }
}
