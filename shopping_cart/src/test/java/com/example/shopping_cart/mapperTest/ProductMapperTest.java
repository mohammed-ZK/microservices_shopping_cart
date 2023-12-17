package com.example.shopping_cart.mapperTest;

import org.mapstruct.Mapper;

import com.example.shopping_cart.dto.ProductDto;
import com.example.shopping_cart.entity.Product;

@Mapper
public interface ProductMapperTest {

	ProductDto mapToDto(Product product);

	Product mapToEntity(ProductDto productDto);

}
