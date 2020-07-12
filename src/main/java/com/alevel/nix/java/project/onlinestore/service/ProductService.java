package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.Category;
import com.alevel.nix.java.project.onlinestore.entity.Manufacturer;
import com.alevel.nix.java.project.onlinestore.entity.Product;
import com.alevel.nix.java.project.onlinestore.entity.request.SaveProductRequest;
import com.alevel.nix.java.project.onlinestore.entity.request.UpdateProductRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.ProductResponse;
import com.alevel.nix.java.project.onlinestore.exception.CategoryNotFoundException;
import com.alevel.nix.java.project.onlinestore.exception.ProductNotFoundException;
import com.alevel.nix.java.project.onlinestore.repository.CategoryRepository;
import com.alevel.nix.java.project.onlinestore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService implements ProductOperations {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponse createProduct(SaveProductRequest saveProductRequest) {
        String categoryName = saveProductRequest.getCategoryName();
        if (!categoryRepository.existsByName(categoryName)) {
            throw new CategoryNotFoundException(categoryName);
        }
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setCompanyName(saveProductRequest.getCompany());
        manufacturer.setSeries(saveProductRequest.getSeries());
        manufacturer.setModelName(saveProductRequest.getModel());
        manufacturer.setProductName(saveProductRequest.getProductName());
        Integer price = saveProductRequest.getPrice();
        String description = saveProductRequest.getDescription();
        Category category = categoryRepository
                .getByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));
        Product product = new Product(description, category, price, manufacturer);

        return new ProductResponse(productRepository.save(product));
    }

    @Override
    public void updateProduct(Long productId, UpdateProductRequest updateProductRequest) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if (updateProductRequest.getCompany() != null) {
            product.getModel().setCompanyName(updateProductRequest.getCompany());
        }
        if (updateProductRequest.getSeries() != null) {
            product.getModel().setSeries(updateProductRequest.getSeries());
        }
        if (updateProductRequest.getModel() != null) {
            product.getModel().setModelName(updateProductRequest.getModel());
        }
        if (updateProductRequest.getProductName() != null) {
            product.getModel().setProductName(updateProductRequest.getProductName());
        }
        if (updateProductRequest.getPrice() != null) {
            product.setPrice(updateProductRequest.getPrice());
        }
        if (updateProductRequest.getDescription() != null) {
            product.setDescription(updateProductRequest.getDescription());
        }
        if (updateProductRequest.getAvailability() != null) {
            product.setAvailability(updateProductRequest.getAvailability());
        }
        productRepository.save(product);

    }




    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : products) {
            productResponseList.add(new ProductResponse(product));
        }
        return productResponseList;
    }

    @Override
    public List<ProductResponse> getAllProductsByAvailability(Boolean availability) {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : products) {
            if (product.getAvailability().equals(availability)) {
                productResponseList.add(new ProductResponse(product));
            }
        }
        return productResponseList;
    }

    @Override
    public ProductResponse getProductById(Long id) {

        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return new ProductResponse(product);
    }
}
