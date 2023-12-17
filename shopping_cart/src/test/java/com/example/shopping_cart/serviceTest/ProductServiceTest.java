package com.example.shopping_cart.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.shopping_cart.entity.Product;
import com.example.shopping_cart.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldInsertProduct() {
        Product product = createTestProduct();
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        Product savedProduct = productRepository.save(product);
        Optional<Product> optional = Optional.of(savedProduct);
        assertTrue(optional.isPresent());
    }

    @Test
    public void shouldGetProducts() {
        Product product = createTestProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
        Mockito.when(productRepository.findAll()).thenReturn(products);

        Product savedProduct = productRepository.save(product);
        List<Product> retrievedProducts = productRepository.findAll();

        assertEquals(products, retrievedProducts);
    }

    // Other test methods...
    @Test
    private Product createTestProduct() {
        Product product = new Product();
        product.setDescription("aaa");
        product.setName("number1");
        product.setPrice(BigDecimal.valueOf(200));
        return product;
    }
}
