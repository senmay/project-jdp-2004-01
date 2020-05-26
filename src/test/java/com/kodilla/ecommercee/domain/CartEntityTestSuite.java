package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartEntityTestSuite {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testCartCreateAndSave() {
        //Given
        Cart cart1 = new Cart("cartname");

        //When
        cartRepository.save(cart1);
        long id = cart1.getId();
        Optional<Cart> cart = cartRepository.findById(id);

        //Then
        Assert.assertTrue(cart.isPresent());

        //CleanUp
        cartRepository.deleteById(id);
    }


    @Test
    public void testDeleteCart() {
        //Given
        Cart cart1 = new Cart("cartname");

        //When
        cartRepository.save(cart1);
        long id = cart1.getId();

        cartRepository.deleteById(id);
        List<Cart> cart = cartRepository.findAll();

        //Then
        Assert.assertEquals(0, cart.size());
    }


    @Test
    public void testAddCartItemToCart() {
        //Given
        Cart cart = new Cart("cartname");
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem());
        cart.setCartItemList(cartItems);

        //When
        cartRepository.save(cart);
        long id = cart.getId();

        List<CartItem> cartItemList = cart.getCartItemList();
        cartItemList.size();

        //Then
        Assert.assertEquals(1, cart.getCartItemList().size());

        //CleanUp
        cartRepository.deleteById(id);
    }

    @Test
    public void testDeleteCartItemFromCart() {
        //Given
        Cart cart = new Cart("cartname");
        Product product = new Product("prod", "desc");
        CartItem item = new CartItem(1L,"CartName",4);
        List<CartItem> items = new ArrayList<>();
        item.setCart(cart);
        items.add(item);
        item.setProduct(product);
        product.getCartItemList().add(item);

        //When
        cartRepository.save(cart);
        cartItemRepository.save(item);

        long id = cart.getId();
        List<CartItem> all = cartItemRepository.findAll();
        cartItemRepository.deleteById(all.get(0).getId());

        //Then
        Assert.assertEquals(0, cartItemRepository.findAll().size());
        Assert.assertTrue(cartRepository.findAll().size()==1);

        //CleanUp
        cartRepository.deleteById(id);
    }


    @Test
    public void testAddUserToCart() {
        //Given
        Cart cart = new Cart("cartname");
        User user = new User(111L,"userName",true,"apikey");
        cart.setUser(user);

        //When
        cartRepository.save(cart);
        userRepository.save(user);
        long id = cart.getId();

        //Then
        Assert.assertEquals("userName", cart.getUser().getUsername());

        //CleanUp
        cartRepository.deleteById(id);
    }

    @Test
    public void testDelateUserFromCart() {
        //Given
        Cart cart = new Cart("cartname");
        User user = new User(111L,"userName",true,"apikey");
        List<User> users = new ArrayList<>();
        users.add(user);
        cart.setUser(user);
        user.setCart(cart);

        //When
        cartRepository.save(cart);
        userRepository.save(user);
        long id = cart.getId();
        userRepository.deleteById(users.get(0).getUserId());

        //Then
        Assert.assertEquals(0, userRepository.findAll().size());
        Assert.assertEquals(0, cartRepository.findAll().size());

    }
}
