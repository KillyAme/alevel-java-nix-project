package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends OnlineStoreException {
    public ProductNotFoundException(Long productId) {
        super(HttpStatus.NOT_FOUND, "Product with id: " + productId + "not found");
    }
}
