package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.aspectj.weaver.ast.Or;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;
    private User user1;
    private User user2;

    @Before
    public void createUsers() {
        user1 = new User(1L, "Imie1", false, "token1");
        user2 = new User(2L, "imie2", true, "token2");
    }

    @After
    public void cleanAfterTest() {
        try {
            userRepository.deleteAll();
            cartRepository.deleteAll();
            orderRepository.deleteAll();
            groupRepository.deleteAll();
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testFindUserSize() {
        //Given
        userRepository.save(user1);
        userRepository.save(user2);
        //When
        List<User> users = userRepository.findAll();
        //Then
        assertEquals(2, users.size());

    }

    @Test
    public void testSaveUser() {
        //Given
        User user = new User();
        //When
        userRepository.save(user);
        Long userId = user.getId();
        //Then
        assertNotEquals(0, (long) userId);
    }

    @Test
    public void testFindUserById() {
        //Given
        userRepository.save(user1);
        userRepository.save(user2);
        Long userIdToFind = user1.getId();
        //When
        Optional<User> userOptional = userRepository.findById(userIdToFind);
        //Then
        assertTrue(userOptional.isPresent());
        assertEquals(user1, userOptional.get());
    }

    @Test
    public void testDeleteById() {
        //Given
        userRepository.save(user1);
        userRepository.save(user2);
        Long userIdNeedToFind = user2.getId();
        Long userIdToDelete= user1.getId();
        //When
        userRepository.deleteById(userIdToDelete);
        List<User> users = userRepository.findAll();
        Optional<User> optionalUser = userRepository.findById(userIdNeedToFind);
        //Then
        assertTrue(optionalUser.isPresent());
        assertEquals(user2, optionalUser.get());
        assertEquals(1, users.size());
    }

    @Test
    public void testShouldSaveUserWithOrders() {
        //Given
        Order order1 = new Order("order1");
        Order order2 = new Order("order2");
        Order order3 = new Order("order3");
        order1.setUser(user1);
        order2.setUser(user1);
        order3.setUser(user1);
        user1.getOrders().add(order1);
        user1.getOrders().add(order2);
        user1.getOrders().add(order3);
        userRepository.save(user1);

        //When
        List<User> user = userRepository.findAll();
        List<Order> ordersInUser = user.get(0).getOrders();

        //Then
        assertEquals(3, ordersInUser.size());
    }

    @Test
    public void testShouldDeleteOrder() {
        //Given
        Order order1 = new Order("order1");
        Order order2 = new Order("order2");
        Order order3 = new Order("order3");
        order1.setUser(user1);
        order2.setUser(user1);
        order3.setUser(user1);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        user1.getOrders().add(order1);
        user1.getOrders().add(order2);
        user1.getOrders().add(order3);
        userRepository.save(user1);

        //When
        userRepository.deleteById(user1.getId());
        List<User> user = userRepository.findAll();
        List<Order> orders = orderRepository.findAll();

        //Then
        assertEquals(0, orders.size());
    }

    @Test
    public void testShouldNotDeleteGroup() {
        //Given
        Group group = new Group("groupNameTest");
        userRepository.save(user1);
        groupRepository.save(group);

        //When
        userRepository.deleteById(user1.getId());
        List<Group> groups = groupRepository.findAll();

        //Then
        assertEquals(1, groups.size());
    }

    @Test
    public void testShouldDeleteCart() {

        //Given
        Cart cart = new Cart("cart1");
        cart.setUser(user1);
        cartRepository.save(cart);
        user1.setCart(cart);
        userRepository.save(user1);

        //When
        userRepository.deleteById(user1.getId());
        List<Cart> carts = cartRepository.findAll();

        //Then
        assertEquals(0, carts.size());
    }
}
