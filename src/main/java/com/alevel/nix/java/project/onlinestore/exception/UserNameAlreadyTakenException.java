package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class UserNameAlreadyTakenException extends OnlineStoreException {

    public UserNameAlreadyTakenException(String userName) {
        super(HttpStatus.BAD_REQUEST, "Username: " + userName + "already exist");
    }
}
