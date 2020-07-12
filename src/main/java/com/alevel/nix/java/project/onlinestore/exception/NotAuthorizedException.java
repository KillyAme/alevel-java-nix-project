package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class NotAuthorizedException extends OnlineStoreException {
    public NotAuthorizedException(String reason) {
        super(HttpStatus.FORBIDDEN, reason);
    }
}
