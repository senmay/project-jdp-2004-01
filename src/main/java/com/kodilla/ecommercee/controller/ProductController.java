package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;

import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.DbProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private DbProductService dbProductService;
    @Autowired
    private ProductMapper productMapper;


    @RequestMapping(method = RequestMethod.POST, value = "createProduct")
    public void createProduct(ProductDto productDto) {
        dbProductService.saveProduct(productMapper.mapToProduct(productDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(Long productId) {
        return productMapper.mapToProductDto(dbProductService.getById(productId));
    }

    @RequestMapping(method = RequestMethod.GET, value ="getProducts" )
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductListDto(dbProductService.getAllProducts());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(ProductDto productDto) throws ProductNotFoundException {
        return productMapper.mapToProductDto(dbProductService.updateProduct(productMapper.mapToProduct(productDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        dbProductService.deleteProduct(productId);
    }

}
