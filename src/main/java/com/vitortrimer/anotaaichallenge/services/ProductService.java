package com.vitortrimer.anotaaichallenge.services;

import com.vitortrimer.anotaaichallenge.domain.products.Product;
import com.vitortrimer.anotaaichallenge.domain.products.ProductDTO;
import com.vitortrimer.anotaaichallenge.domain.products.exceptions.ProductNotFoundException;
import com.vitortrimer.anotaaichallenge.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public Product insert(ProductDTO productData) {
        Product newProduct = new Product(productData);
        this.repository.save(newProduct);

        return newProduct;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public Product update(String id, ProductDTO productData) {
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
//        if(!productData.categoryId().isEmpty()) product.setCategory(); -- TODO
        product.setPrice(productData.price());

        this.repository.save(product);
        return product;
    }

    public void delete(String id) {
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }
}