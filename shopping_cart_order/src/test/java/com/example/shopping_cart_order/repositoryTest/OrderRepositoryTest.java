package com.example.shopping_cart_order.repositoryTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart_order.entity.Order;

@Repository
public interface OrderRepositoryTest extends JpaRepository<Order, Long>{

}

