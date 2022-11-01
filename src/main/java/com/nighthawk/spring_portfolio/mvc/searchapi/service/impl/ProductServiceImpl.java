package com.nighthawk.spring_portfolio.mvc.searchapi.service.impl;


import com.nighthawk.spring_portfolio.mvc.searchapi.products.Product;
import com.nighthawk.spring_portfolio.mvc.searchapi.repository.ProductRepository;
import com.nighthawk.spring_portfolio.mvc.searchapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> searchProducts(String query) {
        List<Product> products = productRepository.searchProducts(query);
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}