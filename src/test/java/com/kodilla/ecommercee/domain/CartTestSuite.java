package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;


    @Test
    public void testSaveCart() {

        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);
        Optional<Cart> testCart = cartRepository.findById(cart.getId());

        //Then
        Assert.assertTrue(testCart.isPresent());

        //CleanUp
        cartRepository.deleteById(cart.getId());

    }

}