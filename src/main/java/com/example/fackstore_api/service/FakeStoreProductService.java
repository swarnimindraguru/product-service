package com.example.fackstore_api.service;

import com.example.fackstore_api.dtos.FakeStoreProductDto;
import com.example.fackstore_api.exceptions.ProductNotFoundException;
import com.example.fackstore_api.models.Category;
import com.example.fackstore_api.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setTitle(dto.getDescription());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException{
//        int i = 0/0;
//        Call fakeStore API here to get the product with the given id
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException(id, "Product for id "+ id + " not found");
        }

        //FakeStore DTO into Product object
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
//        throw new RuntimeException("Something went wrong");
    }

    @Override
    public List<Product> getAllProducts() {
       FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        //List of FakeStoreProductDto to product
        List<Product> response = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            response.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return response;
    }
    @Override
    public Product replaceProduct(Long id,Product product){
//        restTemplate.
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
