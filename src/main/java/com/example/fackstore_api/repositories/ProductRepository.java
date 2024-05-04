package com.example.fackstore_api.repositories;

import com.example.fackstore_api.models.Category;
import com.example.fackstore_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    @Override
    //We use optional to avoid NullPointerException
    Optional<Product> findById(Long id);

    List<Product> findByTitle(String word);
//    List<Product> findByCategory(String str);
    List<Product> findByTitleAndDescription(String title, String description);

    @Override
    void delete(Product entity);
    Product save(Product product); //create and update

//    HQL
//    @Query("select p.title as title, p.description as description from Product p where p.id = :id")
//    ProductWithTitleAndDescription someRandomQuery(@Param("id") Long id);
//
//    //SQL Query -> native query
//    @Query(value = "select title, description from product where id = :id", nativeQuery = true)
//    ProductWithTitleAndDescription someOtherRandomQuery(@Param("id") Long id);
}
