package com.example.fackstore_api.service;

import com.example.fackstore_api.exceptions.ProductNotFoundException;
import com.example.fackstore_api.models.Category;
import com.example.fackstore_api.models.Product;
import com.example.fackstore_api.repositories.CategoryRepository;
import com.example.fackstore_api.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Fetch the product from db with given id from db
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(id, "Product not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        //Before saving the Product object in the db, save the category object first
        Optional<Category> optionalCategory = categoryRepository.findById(product.getCategory().getId());
        if(optionalCategory.isEmpty()) {
            //we need to save the category first
            Category newcategory = new Category();
            newcategory.setTitle(product.getCategory().getTitle());
            categoryRepository.save(newcategory);
            product.setCategory(newcategory);
        } else {
            Category category1 = optionalCategory.get();
            product.setCategory(category1);
        }
//        product.setCreatedAt(LocalDateTime.now());
//        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
