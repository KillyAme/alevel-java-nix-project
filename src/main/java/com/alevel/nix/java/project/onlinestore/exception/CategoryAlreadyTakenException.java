package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class CategoryAlreadyTakenException extends OnlineStoreException {
    public CategoryAlreadyTakenException(String name) {
        super(HttpStatus.BAD_REQUEST, "Category with name: " + name + " already exist.");
    }
}
