package com.example.shopping_cart_order.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.example.shopping_cart_order.controller.OrderController;
import com.example.shopping_cart_order.entity.Order;
import com.example.shopping_cart_order.exception.BaseResponse;
import com.example.shopping_cart_order.service.OrderService;

@WebMvcTest(OrderControllerTest.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testInsertOrder() throws Exception {
        // Arrange
        Order order = new Order(1L,1L,1L);
        when(orderService.insert(any(Order.class))).thenReturn(new BaseResponse<>());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/order/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"property1\":\"value1\",\"property2\":\"value2\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("8081"))
                .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        // Arrange
        List<Order> orders = Arrays.asList(new Order(1L,1L,1L));
        BaseResponse<List<Order>> baseResponse=new BaseResponse<>();
        baseResponse.setData(orders);
        when(orderService.getAllOrders()).thenReturn(baseResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/order/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("8081"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testGetOrderById() throws Exception {
        // Arrange
        Long orderId = 1L;
        Order order = new Order(1L,1L,1L);
        BaseResponse<Order> baseResponse=new BaseResponse<>();
        baseResponse.setData(order);
        when(orderService.getOrderById(orderId)).thenReturn(baseResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/order/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("8081"))
                .andExpect(jsonPath("$.data").isMap());
    }
}


