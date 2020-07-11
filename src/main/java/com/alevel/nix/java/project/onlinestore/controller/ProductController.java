package com.alevel.nix.java.project.onlinestore.controller;

import com.alevel.nix.java.project.onlinestore.entity.request.SaveProductRequest;
import com.alevel.nix.java.project.onlinestore.entity.request.UpdateProductRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.ProductResponse;
import com.alevel.nix.java.project.onlinestore.service.ProductOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductOperations productOperations;

    public ProductController(ProductOperations productOperations) {
        this.productOperations = productOperations;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> create(@RequestBody SaveProductRequest request) {
        ProductResponse productResponse = productOperations.createProduct(request);
        return ResponseEntity
                .created(URI.create("/products/" + productResponse.getId()))
                .body(productResponse);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        productOperations.updateProduct(id, request);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productOperations.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id){
        return productOperations.getProductById(id);
    }

}
