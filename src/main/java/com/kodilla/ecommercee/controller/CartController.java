package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public void createCart(CartDto cartDto) {
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts(Long cartId) {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProduct")
    public void addProduct(Long cartId, Long productId){
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(Long cartId, Long productId) {
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(Long cartId) {
    }

}