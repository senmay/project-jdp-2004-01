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
        Assert.assertEquals(1, id);
        Assert.assertTrue(cart.isPresent());

        //CleanUp
        cartRepository.deleteById(id);
    }
}
