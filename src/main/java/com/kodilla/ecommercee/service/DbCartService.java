package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.CartNotFoundException;
import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
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
import java.util.Optional;

@Component
@Service
public class DbCartService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DbCartService.class);
    private static final String INFORMATION = "Product not found";


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<CartItem> getCartItems(final Long cartId) throws CartNotFoundException {

        Optional<Cart> checkCartId = cartRepository.findById(cartId);

        if (checkCartId.isPresent()) {
            return checkCartId.get().getCartItemList();
        } else {
            LOGGER.error("No cart with ID: " + cartId + " found");
            throw new CartNotFoundException();
        }
    }

    public void addProduct(final Long id, final Long cartId) throws CartNotFoundException, ProductNotFoundException {

        Optional<Cart> checkCartId = cartRepository.findById(cartId);
        Optional<Product> checkProductId = productRepository.findById(id);

        if (checkCartId.isPresent()) {
            if (checkProductId.isPresent()) {
                CartItem cartItem = new CartItem();
                cartItem.setCart(checkCartId.get());
                checkCartId.get().getCartItemList().add(cartItem);
                cartItemRepository.save(cartItem);
                LOGGER.info("Successfully added new product to cart with ID: " + cartId);
            } else {
                LOGGER.error("No cart with ID: " + cartId + " found");
                throw new CartNotFoundException();
            }
            } else {
                LOGGER.error("No product with ID: " + id + " found");
                throw new ProductNotFoundException(INFORMATION);
            }
        }

    public void deleteProduct(final Long cartItemId, final Long cartId) throws CartNotFoundException, ProductNotFoundException {

        Optional<Cart> checkCartId = cartRepository.findById(cartId);
        Optional<CartItem> checkCartItemId = cartItemRepository.findById(cartItemId);

        if (checkCartId.isPresent()) {

            if (checkCartItemId.isPresent()) {
                CartItem cartItem = checkCartItemId.get();
                Cart cart = checkCartId.get();

                if (cart.getCartItemList().contains(cartItem)) {
                    cart.getCartItemList().remove(cartItem);
                    productRepository.findById(cartItem.getProduct().getId()).get().getCartItemList().remove(cartItem);
                    cartItemRepository.deleteById(cartItemId);
                    LOGGER.info("Successfully deleted CartItem-ID: " + cartItemId + " from Cart");
                } else {
                    LOGGER.warn("Cart-ID: " + cart.getCartItemList() + "not contain Item: " + cartItem.getId());
                    throw new ProductNotFoundException(INFORMATION);
                }
                } else {
                    LOGGER.error("No cart with ID: " + cartId + " found");
                    throw new CartNotFoundException();
                }
            }
        }


    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public void createOrder(final Long cartId) throws CartNotFoundException {

        Optional<Cart> checkCartId = cartRepository.findById(cartId);

        if (checkCartId.isPresent()) {
            Order order = new Order();
            order.setUser(checkCartId.get().getUser());
            checkCartId.get().getCartItemList();
            orderRepository.save(order);
            LOGGER.info("Successfully created order with ID: " + order.getId());
        } else {
            LOGGER.error("No found cart-ID: " + cartId);
            throw new CartNotFoundException();
        }
    }
}
