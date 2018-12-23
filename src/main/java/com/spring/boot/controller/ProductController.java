package com.spring.boot.controller;

import com.spring.boot.model.Product;
import com.spring.boot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test() {
        return "HELLO WORL";
    }

    @RequestMapping(method= RequestMethod.GET, value = "/products")
    public Iterable<Product> product() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    Product product la gia tri dc bundle trong @ResponseBody
    public @ResponseBody String saveProduct(
            Product product) {
        productRepository.save(product);
        return product.getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product/{idProduct}")
    public Product getInfo(@PathVariable String idProduct){
        return productRepository.findOne(idProduct);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/remove-product/{idProduct}")
    public String removeProduct(@PathVariable String idProduct) {
        Product product = productRepository.findOne(idProduct);
        productRepository.delete(product);
        return "Remove Success";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update-product/{idProduct}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody Product updateProduct(@PathVariable String idProduct, Product product) {
        Product productUpdate = productRepository.findOne(idProduct);

        if (product.getProductName() != null) {
            productUpdate.setProductName(product.getProductName());
        }
        if (product.getProductDescription() != null) {
            productUpdate.setProductDescription(product.getProductDescription());
        }
        if (product.getProductPrice() != null) {
            productUpdate.setProductPrice(product.getProductPrice());
        }
        if (product.getProductImage() != null) {
            productUpdate.setProductImage(product.getProductImage());
        }
        if (product.getIdCategory() != null) {
            productUpdate.setIdCategory(product.getIdCategory());
        }
        productRepository.save(productUpdate);
        return productUpdate;
    }
}
