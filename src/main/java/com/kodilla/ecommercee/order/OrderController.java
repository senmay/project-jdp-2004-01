package com.kodilla.ecommercee.order;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrderList() {
        return new ArrayList<>();
    }

    @PostMapping(value = "addOrder", consumes = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestParam OrderDto orderDto) {
    }

    @GetMapping(value = "getOrder")
    public OrderDto getOrder(@RequestParam long id) {
        return new OrderDto();
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestParam OrderDto orderDto) {
        return new OrderDto();
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(@RequestParam long id) {
    }
}
