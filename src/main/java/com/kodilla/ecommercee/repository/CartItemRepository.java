package com.kodilla.ecommercee.repository;
import com.kodilla.ecommercee.domain.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Transactional
@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    @Override
    Optional<CartItem> findById(Long id);

    @Override
    void deleteById(Long id);
}