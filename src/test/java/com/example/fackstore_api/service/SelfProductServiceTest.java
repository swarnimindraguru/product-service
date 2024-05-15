package com.example.fackstore_api.service;

import com.example.fackstore_api.exceptions.ProductNotFoundException;
import com.example.fackstore_api.models.Product;
import com.example.fackstore_api.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SelfProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void validateGetProductById() throws ProductNotFoundException {

        Long nonExistentProductId = 123L;
        when(productRepository.findById(nonExistentProductId)).thenReturn(Optional.empty());
        productService.getProductById(nonExistentProductId);

        // or

//        assertThrows(ProductNotFoundException.class,()-> productService.getProductById(nonExistentProductId));

    }
}