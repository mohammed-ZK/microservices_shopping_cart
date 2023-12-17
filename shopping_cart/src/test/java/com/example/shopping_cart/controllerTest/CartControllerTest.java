package com.example.shopping_cart.controllerTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.shopping_cart.Exception.BaseResponse;
import com.example.shopping_cart.controller.CartController;
import com.example.shopping_cart.dto.CartDto;
import com.example.shopping_cart.entity.Product;
import com.example.shopping_cart.service.CartService;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testInsert() throws Exception {
        // Arrange
        when(cartService.insert()).thenReturn(1L);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("8088"))
                .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    public void testGetCarts() throws Exception {
        // Arrange
        List<CartDto> carts = Arrays.asList(new CartDto(/* set cart properties */));
        when(cartService.getCarts()).thenReturn(carts);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].property").value("value")); // Adjust based on your CartDto properties
    }

    @Test
    public void testGetCartById() throws Exception {
        // Arrange
        Long cartId = 1L;
        Product product1=new Product(1L, "phone", "wqwq", BigDecimal.valueOf(2000));
        Product product2=new Product(2L, "labtop", "sad", BigDecimal.valueOf(4000));
        List<Product> listProduct=new ArrayList<>();
        listProduct.add(product1);
        listProduct.add(product2);
        CartDto cart = new CartDto();
        cart.setId(1L);
        cart.setTotalprice(BigDecimal.valueOf(6000));
        cart.setProducts(listProduct);
        BaseResponse<CartDto> baseResponse=new BaseResponse<>();
        baseResponse.setData(cart);
        when(cartService.getCart(cartId)).thenReturn(baseResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.name").value("expectedName")); // Adjust based on your CartDto properties

    }

    @Test
    public void testDeleteCart() throws Exception {
        // Arrange
        Long cartId = 1L;

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/cart/1"))
                .andExpect(status().isOk());
        
        verify(cartService, times(1)).deleteCart(cartId);
    }
}



