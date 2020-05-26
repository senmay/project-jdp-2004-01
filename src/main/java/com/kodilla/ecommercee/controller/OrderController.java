package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.DbOrderService;
import com.kodilla.ecommercee.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    DbOrderService dbOrderService;
    @Autowired
    OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrderList() {
        return orderMapper.mapToOrderDtoList(dbOrderService.getAllOrders());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addOrder", consumes =
            APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        dbOrderService.save(orderMapper.mapToOrder(orderDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam long id) {
        return orderMapper.mapToOrderDto(dbOrderService.getById(id).orElseThrow(OrderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(dbOrderService.save(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@RequestParam long id) {
        if (dbOrderService.deleteById(id) == false) {
            throw new OrderNotFoundException();
        }
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleContentNotAllowedException(OrderNotFoundException t) {
    }
}
