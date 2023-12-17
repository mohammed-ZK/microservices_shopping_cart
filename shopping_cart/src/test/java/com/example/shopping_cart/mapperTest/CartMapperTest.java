package com.example.shopping_cart.mapperTest;

import org.mapstruct.Mapper;

import com.example.shopping_cart.dto.CartDto;
import com.example.shopping_cart.entity.Cart;

@Mapper
public interface CartMapperTest {

	CartDto mapToDto(Cart cart);

	Cart mapToEntity(CartDto cartDto);

}
