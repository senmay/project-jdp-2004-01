package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.mapper.CartItemMapper;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.DbCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private DbCartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @PostMapping(value = "createCart", consumes = APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
        cartService.saveCart(cartMapper.mapToCart(cartDto));
    }

    @GetMapping(value = "getProducts")
    public List<CartItemDto> getProducts(@RequestParam Long id) throws CartNotFoundException {
            return cartItemMapper.mapToItemsDtoList(cartService.getCartItems(id));
    }

    @PutMapping(value = "addProduct", consumes = APPLICATION_JSON_VALUE)
    public void addProduct(@RequestParam Long id, @RequestParam Long cartId) throws CartNotFoundException, ProductNotFoundException{
        cartService.addProduct(id, cartId);
    }

    @DeleteMapping(value = "deleteProduct", consumes = APPLICATION_JSON_VALUE)
    public void deleteProduct(@RequestParam Long cartItemId,@RequestParam Long cartId) throws CartNotFoundException, ProductNotFoundException{
        cartService.deleteProduct(cartItemId,cartId);
    }

    @PostMapping(value = "createOrder", consumes = APPLICATION_JSON_VALUE )
    public void createOrder(@RequestParam Long cartId) throws CartNotFoundException {
        cartService.createOrder(cartId);
    }

}