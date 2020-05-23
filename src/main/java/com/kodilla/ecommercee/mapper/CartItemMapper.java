package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.dto.CartItemDto;
import com.kodilla.ecommercee.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemMapper {

    @Autowired
    CartItemRepository cartItemRepository;

    public List<CartItemDto> mapToItemsDtoList(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(i -> new CartItemDto(i.getId(), i.getName(), i.getQuantity(), i.getProduct().getId()))
                .collect(Collectors.toList());
    }
}
