package com.kodilla.ecommercee.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDto {
    private long id;
    private String name;
    private List<OrderDto> orderList = new ArrayList<>();
}
