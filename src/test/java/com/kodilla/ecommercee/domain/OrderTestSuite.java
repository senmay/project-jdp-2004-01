package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
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
        Order order = new Order("order1");

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
        Order order = new Order("order1");

        //When
        orderRepository.save(order);
        long id = order.getId();
        orderRepository.deleteById(id);
        List<Order> orderList = orderRepository.findAll();

        //Then
        Assert.assertEquals(0,orderList.size());
    }

    @Test
    public void testUpdateOrder() {
        //Given
        Order order = new Order("order1");

        //When
        orderRepository.save(order);
        long id = order.getId();
        order.setName("order2");
        orderRepository.save(order);
        String name = orderRepository.findById(id).get().getName();

        //Then
        Assert.assertEquals(name,"order2");

        //Clean up
        orderRepository.deleteById(id);
    }

    @Test
    public void testRelationWithDeleteOrder() {
        //Given
        Product product = new Product("product1", "description1");
        OrderItem orderItem = new OrderItem("OrderItem1",2);
        Order order = new Order("order1");

        orderItem.setOrder(order);
        order.getOrderItemList().add(orderItem);
        orderItem.setProduct(product);
        product.getOrderItemList().add(orderItem);

        //When
        orderRepository.save(order);
        long orderId = order.getId();
        long productId = product.getId();
        long orderItemId = orderItem.getId();
        orderRepository.deleteById(orderId);
        Optional<Product> products = productRepository.findById(productId);
        Optional<OrderItem> orderItems = orderItemRepository.findById(orderItemId);

        //Then
        Assert.assertEquals(1, products.get().getOrderItemList().size());
        Assert.assertTrue(orderItems.isPresent());

        //Clean up
        orderItemRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();
    }
}
