package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyTakenException extends OnlineStoreException {
    public EmailAlreadyTakenException(String email) {
        super(HttpStatus.BAD_REQUEST, "Email: " + email + "already exist");
    }
}
