package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OnlineStoreException extends ResponseStatusException {

    public OnlineStoreException(HttpStatus status) {
        super(status);
    }

    public OnlineStoreException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public OnlineStoreException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
