package com.example.fackstore_api.controllers;

import com.example.fackstore_api.exceptions.ProductNotFoundException;
import com.example.fackstore_api.models.Product;
import com.example.fackstore_api.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController // this controller is going to RestHTTP Api's
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
//        return productService.getProductById(id);
        Product product = productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
        responseEntity = new ResponseEntity<>(product,  HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
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
