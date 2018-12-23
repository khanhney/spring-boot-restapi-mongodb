package com.spring.boot.repository;

import com.spring.boot.model.Category;
import com.spring.boot.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findById(String id);
    List<Product> findCategoryBy(String idCategory);
}
