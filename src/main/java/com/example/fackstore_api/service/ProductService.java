package com.example.fackstore_api.service;

import com.example.fackstore_api.exceptions.ProductNotFoundException;
import com.example.fackstore_api.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product replaceProduct(Long id,Product product);
    Product updateProduct(Long id, Product product);
    Product createProduct(Product product);
    void deleteProduct(Long id);
    }
