package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order mapToOrder(final OrderDto OrderDto){
        return new Order(
                OrderDto.getId(),
                OrderDto.getName()
        );
    }

    public OrderDto mapToOrderDto(final Order Order){
        return new OrderDto(
                Order.getId(),
                Order.getName()
        );
    }
    public List<OrderDto> mapToOrderDtoList(final List<Order> OrderList){
        return OrderList.stream()
                .map(t->new OrderDto(t.getId(),t.getName()))
                .collect(Collectors.toList());
    }
}