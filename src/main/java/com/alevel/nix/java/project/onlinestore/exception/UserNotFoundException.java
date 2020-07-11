package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends OnlineStoreException {
    public UserNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "User with " + id + " not found.");
    }
}
