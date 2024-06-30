package com.example.fackstore_api.controllers;

import com.example.fackstore_api.commons.AuthCommons;
import com.example.fackstore_api.dtos.UserDto;
import com.example.fackstore_api.exceptions.ProductNotFoundException;
import com.example.fackstore_api.models.Product;
import com.example.fackstore_api.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController // this controller is going to RestHTTP Api's
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthCommons authCommons;

    ProductController(@Qualifier("selfProductService") ProductService productService,AuthCommons authCommons){
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {

// commented this as we already added spring.security.oauth2.resourceserver.jwt.issuer-uri=https://localhost:8181 in application.prop
        //Call UserService ValidateToken API to validate the token.
//        UserDto userDto = authCommons.validateTocken(token);
        ResponseEntity<Product> responseEntity;
//        if (userDto == null){
//            responseEntity =new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
//            return responseEntity;
//        }
        Product product = productService.getProductById(id);
        responseEntity = new ResponseEntity<>(product,  HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return productService.getAllProducts(pageNumber,pageSize);
    }

    //create Product
    //delete Product
    //update product --- PATCH
    //replace product --- PUT

    @PutMapping("/{id}")
    public Product replcaeProduct(@PathVariable("id")Long id, @RequestBody Product product){
    return productService.replaceProduct(id,product);
    }

    //Controller level exceptions (this will be called first then the ControllerAdviceExceptions will be called)
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Void> handleSomeException() {
        return null;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
//    public ResponseEntity<Product> createProduct(@RequestBody Product product){ // we can use DTO also if available
//        Product productResponse = productService.createProduct(product);
//        ResponseEntity<Product> responseEntity;
//        responseEntity = new ResponseEntity<>(productResponse,  HttpStatus.OK);
//        return responseEntity;
        return productService.createProduct(product);
    }
}
