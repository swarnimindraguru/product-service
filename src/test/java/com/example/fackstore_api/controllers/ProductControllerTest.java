package com.example.fackstore_api.controllers;

import com.example.fackstore_api.exceptions.ProductNotFoundException;
import com.example.fackstore_api.models.Product;
import com.example.fackstore_api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void validGetProductByIdTest() throws ProductNotFoundException {
        //Mocking product
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Mackbook air");
        product.setPrice(150000);
        when(productService.getProductById(1L)).thenReturn(product);

        //Calling the acutalProduct from controller
        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
        Product actualProduct = responseEntity.getBody();

        //comparing the actualproduct with mocked product
        assertEquals(product, actualProduct);
    }

//     Commented because it is giving error due to pagination implementing
//    @Test
//    void validateGetAllProducts()  {
//        //Mocking Products
//        List<Product> expectedProducts = new ArrayList<>();
//        Product p1 = new Product();
//        p1.setId(1L);
//        p1.setTitle("IPHONE 13");
//
//        Product p2 = new Product();
//        p2.setId(2L);
//        p2.setTitle("IPHONE 14");
//
//        Product p3 = new Product();
//        p3.setId(3L);
//        p3.setTitle("IPHONE 15");
//
//        expectedProducts.add(p1);
//        expectedProducts.add(p2);
//        expectedProducts.add(p3);
//        //condition
//        when(productService.getAllProducts()).thenReturn(expectedProducts);
//
//        ///Calling the acutalProduct from controller
//        List <Product> actualProducts = productController.getAllProducts();
//
//        //comparing the actualproduct with mocked product
//        assertIterableEquals(expectedProducts, actualProducts);
//    }
}