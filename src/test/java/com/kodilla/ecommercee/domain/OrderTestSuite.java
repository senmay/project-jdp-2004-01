package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testAddOrder() {
        //Given
        Order order = new Order(0L,"order");

        //When
        orderRepository.save(order);
        long id = order.getId();
        Optional<Order> readOrder = orderRepository.findById(id);

        //Then
        Assert.assertTrue(readOrder.isPresent());
        //Clean up
        orderRepository.deleteById(id);
    }

    @Test
    public void testDeleteOrder() {
        //Given
        Order order = new Order(0L,"order");
        orderRepository.save(order);

        //When
        long id = order.getId();
        orderRepository.deleteById(id);
        Optional<Order> readOrder = orderRepository.findById(id);
        List<Order> orderList = orderRepository.findAll();

        //Then
        Assert.assertEquals(0,orderList.size());
        Assert.assertFalse(readOrder.isPresent());
    }

    @Test
    public void testUpdateOrder() {
        //Given
        Order order = new Order(0L,"order");
        orderRepository.save(order);

        //When
        long id = order.getId();
        order.setName("order1");
        String name = order.getName();

        //Then
        Assert.assertEquals(name,"order1");

        //Clean up
        orderRepository.deleteById(id);
    }

    @Test
    public void testRelationWithDeleteOrder() {
        //Given
        Product product = new Product("product1", "description1");
        OrderItem orderItem = new OrderItem(0L, "orderItem1", 1);
        Order order = new Order(0L, "order");

        orderItem.setOrder(order);
        order.getOrderItemList().add(orderItem);
        orderItem.setProduct(product);
        product.getOrderItemList().add(orderItem);

        orderRepository.save(order);

        //When
        long id = order.getId();
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());

        //Then
        Assert.assertEquals(1, optionalProduct.get().getOrderItemList().size());
        Assert.assertEquals(1, optionalOrder.get().getOrderItemList().size());

        //Clean up
        Assert.assertEquals(1,optionalProduct.get().getOrderItemList().size());
        orderRepository.deleteById(id);
    }
}
