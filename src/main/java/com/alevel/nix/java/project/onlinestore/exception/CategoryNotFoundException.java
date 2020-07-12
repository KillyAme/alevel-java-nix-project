package com.alevel.nix.java.project.onlinestore.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends OnlineStoreException {
    public CategoryNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Category with name: " + id + " not found.");
    }
    public CategoryNotFoundException(String categoryName) {
        super(HttpStatus.NOT_FOUND, "Category with name: " + categoryName + " not found.");
    }
}
