package com.example.shopping_cart_order.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.shopping_cart_order.entity.Order;
import com.example.shopping_cart_order.exception.BaseResponse;
import com.example.shopping_cart_order.exception.OrderServiceException;
import com.example.shopping_cart_order.repository.OrderRepository;
import com.example.shopping_cart_order.service.OrderService;

public class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderService orderService;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	public void testInsertOrder() throws OrderServiceException {
		// Arrange
		Order order = new Order(1L,1L,1L);
		when(orderRepository.save(any(Order.class))).thenReturn(order);

		// Act
		BaseResponse<Long> response = orderService.insert(order);

		// Assert
		assertThat(response.getData()).isEqualTo(order.getId());
	}

	@Test
	public void testGetAllOrders() {
		// Arrange
		List<Order> orders = Arrays.asList(new Order(1L,1L,1L));
		when(orderRepository.findAll()).thenReturn(orders);

		// Act
		BaseResponse<List<Order>> response = orderService.getAllOrders();

		// Assert
		assertThat(response.getData()).isEqualTo(orders);
	}

	@Test
	public void testGetOrderById() {
		// Arrange
		Long orderId = 1L;
		Order order = new Order(1L,1L,1L);
		when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

		// Act
		BaseResponse<Order> response = orderService.getOrderById(orderId);

		// Assert
		assertThat(response.getData()).isEqualTo(order);
	}
}


