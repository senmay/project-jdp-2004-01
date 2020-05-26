package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DbProductService {

    private static final String INFORMATION = "Product not found";

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getById (final Long id) throws ProductNotFoundException {
        Optional<Product> checkProductId = productRepository.findById(id);
        if (checkProductId.isPresent()) {
            return checkProductId.get();
        } else {
            throw new ProductNotFoundException(INFORMATION);
        }
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product updateProduct(Product product) throws ProductNotFoundException {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException(INFORMATION);
        }
    }

    public void deleteProduct(final Long id) throws ProductNotFoundException {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(INFORMATION);
        }
    }
}
