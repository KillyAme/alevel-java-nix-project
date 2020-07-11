package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class BasketNotFoundException extends OnlineStoreException {
    public BasketNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Basket for user with id: " + id + " not found.");
    }
}
