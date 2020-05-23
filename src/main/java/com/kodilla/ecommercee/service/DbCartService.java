package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.CartNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class DbCartService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DbCartService.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<CartItem> getCartItems(final Long cartId) throws CartNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            return cartRepository.findById(cartId).get().getCartItemList();
        } else {
            LOGGER.error("No cart with ID: " + cartId + " found");
            throw new CartNotFoundException();
        }
    }

    public void addProduct(final Long id, final Long cartId) throws CartNotFoundException{
        if (cartRepository.findById(cartId).isPresent()) {
            if (productRepository.findById(id).isPresent()) {
                CartItem cartItem = new CartItem();
                cartItem.setCart(cartRepository.findById(cartId).get());
                productRepository.findById(id).get().getCartItemList();
                cartRepository.findById(cartId).get().getCartItemList().add(cartItem);
                cartItemRepository.save(cartItem);
                LOGGER.info("Successfully added new product to cart with ID: " + cartId);
            } else {
                LOGGER.error("No cart with ID: " + cartId + " found");
                throw new CartNotFoundException();
            }
        }
    }
    public void deleteProduct(final Long cartItemId, final Long cartId){
        if (cartRepository.findById(cartId).isPresent()) {

            if (cartItemRepository.findById(cartItemId).isPresent()) {
                CartItem cartItem = cartItemRepository.findById(cartItemId).get();
                Cart cart = cartRepository.findById(cartId).get();

                if (cart.getCartItemList().contains(cartItem)) {
                    cart.getCartItemList().remove(cartItem);
                    productRepository.findById(cartItem.getProduct().getId()).get().getCartItemList().remove(cartItem);
                    cartItemRepository.deleteById(cartItemId);
                    LOGGER.info("Successfully deleted CartItem-ID: " + cartItemId + " from Cart");
                } else {
                    LOGGER.warn("Cart-ID: " + cart.getCartItemList() + "not contain Cart Item-ID: " + cartItem.getId());
                }
            }
        }
    }


    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public void createOrder(final Long cartId) throws CartNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            Order order = new Order();
            order.setUser(cartRepository.findById(cartId).get().getUser());
            order.getOrderItemList();
            cartRepository.findById(cartId).get().getCartItemList();
            orderRepository.save(order);
            LOGGER.info("Successfully created order with ID: " + order.getId());
        } else {
            LOGGER.error("No found with  cart-ID: " + cartId);
            throw new CartNotFoundException();
        }
    }
}
