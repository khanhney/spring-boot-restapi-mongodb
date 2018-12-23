package com.spring.boot.controller;

import com.spring.boot.model.Category;
import com.spring.boot.model.Product;
import com.spring.boot.repository.CategoryRepository;
import com.spring.boot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "/**")
@RequestMapping("/api")

public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private MongoTemplate mongoTemplate;

    private Date date = new Date();

    @RequestMapping(method = RequestMethod.GET, value = "/list-category")
    public Iterable<Category> getList() {
        return categoryRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add-category", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody String saveCategory(Category category) {
        category.setCreateAt(date);
        category.setUpdateAt(date);
        categoryRepository.save(category);
        return category.getId();
    }

    /**
     * @param idCagegory
     * @return Category Info
     */

    @RequestMapping(method = RequestMethod.GET, value = "/category/{idCategory}")
    public Category getInfo(@PathVariable String idCagegory) {
        Category categoryInfo = categoryRepository.findById(idCagegory);
        return categoryInfo;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update-category/{idCategory}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody Category updateCategory(Category category, @PathVariable String idCategory) {
        Category updateCategory = categoryRepository.findById(idCategory);
        if (category.getName() != null) {
            updateCategory.setName(category.getName());
        }
        if (category.getDescription() != null) {
            updateCategory.setName(category.getDescription());
        }
        updateCategory.setStatus(category.getStatus());

        categoryRepository.save(updateCategory);
        return updateCategory;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list-product-with-category/{idCategory}")
    public List<Product> listProductWithCategory(@PathVariable String idCategory) {

        Query query = new Query();
        query.addCriteria(Criteria.where("idCategory").is(idCategory));
        List<Product> products = mongoTemplate.find(query, Product.class);

        return products;
    }
}
