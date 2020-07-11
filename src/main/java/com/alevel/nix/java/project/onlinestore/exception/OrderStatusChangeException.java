package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class OrderStatusChangeException extends OnlineStoreException {
    public OrderStatusChangeException() {
        super(HttpStatus.FORBIDDEN, "Сan not change status when order is canceled or completed.");
    }
}
