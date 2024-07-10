package com.example.fackstore_api.service;

import com.example.fackstore_api.dtos.FakeStoreProductDto;
import com.example.fackstore_api.exceptions.ProductNotFoundException;
import com.example.fackstore_api.models.Category;
import com.example.fackstore_api.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    private RedisTemplate<String,Object> redisTemplate;

    FakeStoreProductService(RestTemplate restTemplate,RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
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

        //calling redis
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCTS_"+id);
        if(product != null){
            return product;  //Cache HIT
        }

//        Call fakeStore API here to get the product with the given id
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException(id, "Product for id "+ id + " not found");
        }
        //FakeStore DTO into Product object
        product = convertFakeStoreDtoToProduct(fakeStoreProductDto);

        //Store the data inside the Redis.
        /*
        Map Name : PRODUCTS
        Id : Key
        Value : Product object
         */
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_"+id, product);
        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        return null;
    }


    //Commented as it is giving error while implementing pagination
//    @Override
//    public List<Product> getAllProducts() {
//       FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
//        //List of FakeStoreProductDto to product
//        List<Product> response = new ArrayList<>();
//        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
//            response.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
//        }
//        return response;
//    }

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
