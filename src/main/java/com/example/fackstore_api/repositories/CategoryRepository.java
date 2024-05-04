package com.example.fackstore_api.repositories;

import com.example.fackstore_api.models.Category;
import com.example.fackstore_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    Optional<Category> findById(Long id);
    Category save(Category category);
}
