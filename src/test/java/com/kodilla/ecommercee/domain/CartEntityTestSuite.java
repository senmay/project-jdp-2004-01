package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartEntityTestSuite {
    @Autowired
    private CartRepository cartRepository;

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
    public void testCartDeleteCart() {
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

        //When
        cartRepository.save(cart);
        long id = cart.getId();
        cart.setCartItemList(cartItems);

        List<CartItem> cartItemList = cart.getCartItemList();
        cartItemList.size();

        //Then
        Assert.assertEquals(1, cart.getCartItemList().size());

        //CleanUp
        cartRepository.deleteById(id);
    }

    @Test
    public void testDeleteCartItemToCart() {
        //Given
        Cart cart = new Cart("cartname");
        List<CartItem> cartItems = new ArrayList<>();
        CartItem item = new CartItem();
        cartItems.add(item);

        //When
        cartRepository.save(cart);
        long id = cart.getId();
        cart.setCartItemList(cartItems);

        List<CartItem> cartItemList = cart.getCartItemList();
        cartItemList.size();
        cartItemList.remove(item);

        //Then
        Assert.assertEquals(0, cart.getCartItemList().size());

        //CleanUp
        cartRepository.deleteById(id);
    }


    @Test
    public void testAddUserToCart() {
        //Given
        Cart cart = new Cart("cartname");
        User user = new User(111L,"userName",true,"apikey");

        //When
        cartRepository.save(cart);
        long id = cart.getId();
        cart.setUser(user);

        //Then
        Assert.assertEquals("userName", cart.getUser().getName());

        //CleanUp
        cartRepository.deleteById(id);
    }
}
