package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends OnlineStoreException {
    public CategoryNotFoundException(String name) {
        super(HttpStatus.NOT_FOUND, "Category with name: " + name + " not found.");
    }
}
