package com.example.shopping_cart.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.shopping_cart.Exception.BaseResponse;
import com.example.shopping_cart.Exception.UserNotFoundException;
import com.example.shopping_cart.dto.CartDto;
import com.example.shopping_cart.entity.Cart;
import com.example.shopping_cart.mapper.CartMapper;
import com.example.shopping_cart.repository.CartRepository;
import com.example.shopping_cart.service.CartService;

public class CartServiceTest {

	@Mock
	private CartRepository cartRepository;

	@Mock
	private CartMapper cartMapper;

	@InjectMocks
	private CartService cartService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testInsert() throws UserNotFoundException {
		// Arrange
		Cart cart = new Cart();
		when(cartRepository.save(any(Cart.class))).thenReturn(cart);

		// Act
		Long cartId = cartService.insert();

		// Assert
		assertThat(cartId).isNotNull();
		verify(cartRepository, times(1)).save(any(Cart.class));
	}

	@Test
	public void testGetCarts() throws Exception {
		// Arrange
		List<Cart> carts = new ArrayList<>();
		when(cartRepository.findAll()).thenReturn(carts);

		// Act
		List<CartDto> cartDtos = cartService.getCarts();

		// Assert
		assertThat(cartDtos).isEmpty();
		verify(cartRepository, times(1)).findAll();
	}

	@Test
	public void testDeleteCart() throws Exception {
		// Arrange
		Long cartId = 1L;
		when(cartRepository.findById(cartId)).thenReturn(Optional.of(new Cart()));

		// Act
		BaseResponse<Void> baseResponse = cartService.deleteCart(cartId);

		// Assert
		assertThat(baseResponse.getMessage()).isNotNull();
		verify(cartRepository, times(1)).deleteById(cartId);
	}

	@Test
	public void testGetCart() throws Exception {
		// Arrange
		Long cartId = 1L;
		Cart cart = new Cart();
		when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

		// Act
		BaseResponse<CartDto> baseResponse = cartService.getCart(cartId);

		// Assert
		assertThat(baseResponse.getData()).isNotNull();
		verify(cartRepository, times(1)).findById(cartId);
	}
}
