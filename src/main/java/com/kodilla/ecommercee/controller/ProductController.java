package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;

import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.DbProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private DbProductService dbProductService;
    @Autowired
    private ProductMapper productMapper;


    @PostMapping(value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        dbProductService.saveProduct(productMapper.mapToProduct(productDto));
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct(@RequestParam Long productId) {
        return productMapper.mapToProductDto(dbProductService.getById(productId));
    }

    @GetMapping(value ="getProducts")
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductListDto(dbProductService.getAllProducts());
    }

    @PutMapping(value = "updateProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) throws ProductNotFoundException {
        return productMapper.mapToProductDto(dbProductService.updateProduct(productMapper.mapToProduct(productDto)));
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) throws ProductNotFoundException {
        dbProductService.deleteProduct(productId);
    }

}
