package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.request.SaveProductRequest;
import com.alevel.nix.java.project.onlinestore.entity.request.UpdateProductRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.ProductResponse;

import java.util.List;

public interface ProductOperations {

    ProductResponse createProduct(SaveProductRequest saveProductRequest);

    void updateProduct(Long productId, UpdateProductRequest updateProductRequest);

    List<ProductResponse> getAllProducts();

    List<ProductResponse> getAllProductsByAvailability(Boolean availability);

    ProductResponse getProductById(Long id);
}
