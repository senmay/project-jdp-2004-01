package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    @Override
    List<CartItem> findAll();

    @Override
    CartItem save(CartItem cart);

    @Override
    void deleteById(Long cartItemId);
}