package com.example.shopping_cart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.Exception.BaseResponse;
import com.example.shopping_cart.Exception.UserNotFoundException;
import com.example.shopping_cart.dto.CartDto;
import com.example.shopping_cart.entity.Cart;
import com.example.shopping_cart.mapper.CartMapper;
import com.example.shopping_cart.mapper.CartMapperImpl;
import com.example.shopping_cart.repository.CartRepository;

@Service
public class CartService {

	private static final Logger log = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private CartRepository cartRepository;
	
	@Value("${server.port}")
	private Long port;

	private CartMapper cartMapper = new CartMapperImpl();

	public Long insert() throws UserNotFoundException {
		Cart cart = new Cart();

		log.info("before Auth get");
		
		cart.setTotalprice(BigDecimal.ZERO);

		Long cartId = cartRepository.save(cart).getId();
		
		return cartId;
	}

	public List<CartDto> getCarts() throws Exception {

		List<Cart> carts = cartRepository.findAll();
		List<CartDto> cartDtos = new ArrayList<>();
		for (Cart cart : carts) {
			CartDto cartDto = cartMapper.mapToDto(cart);
			log.info("=======>" + cartDto.getTotalprice());
			cartDtos.add(cartDto);
		}
		return cartDtos;
	}

	public BaseResponse<Void> deleteCart(Long id) throws Exception {

		Cart cart = cartRepository.findById(id).get();
		log.info("=======>" + cart.getId());
		cartRepository.deleteById(id);
		
		BaseResponse<Void> baseResponse = new BaseResponse<>();
		baseResponse.setMessage(port.toString());
		
		return baseResponse;
	}

	public BaseResponse<CartDto> getCart(Long id) throws Exception {

		Cart cart = cartRepository.findById(id).get();
		log.info("=======>" + cart.getId().toString());

		CartDto cartDto = cartMapper.mapToDto(cart);
		BaseResponse<CartDto> baseResponse = new BaseResponse<>();
		baseResponse.setData(cartDto);
		baseResponse.setMessage(port.toString());
		return baseResponse;

	}

}
//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//String string = authentication.getName();
//
//user = userRepository.findByUsername(string).get();