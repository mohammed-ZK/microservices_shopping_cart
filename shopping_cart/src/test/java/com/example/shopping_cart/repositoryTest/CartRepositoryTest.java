package com.example.shopping_cart.repositoryTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart.entity.Cart;

@Repository
public interface CartRepositoryTest extends JpaRepository<Cart, Long> {
}
