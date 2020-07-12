package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class ProductAvailabilityException extends OnlineStoreException {
    public ProductAvailabilityException(Long id) {
        super(HttpStatus.CONFLICT, "Product with id:" + id + "not available");
    }
}
