
package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    @Override
    Optional<Cart> findById(Long aLong);

    @Override
    List<Cart> findAll();

    @Override
    void deleteById(Long cartId);
}
