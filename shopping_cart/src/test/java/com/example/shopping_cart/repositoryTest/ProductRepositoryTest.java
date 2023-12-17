package com.example.shopping_cart.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import com.example.shopping_cart.entity.Product;
import com.example.shopping_cart.repository.ProductRepository;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void testFindByName() {
		// Arrange
		Product product = new Product();
		product.setName("TestProduct");
		testEntityManager.persistAndFlush(product);

		// Act
		Product foundProduct = productRepository.findByName("TestProduct");

		// Assert
		assertThat(foundProduct).isNotNull();
		assertThat(foundProduct.getName()).isEqualTo("TestProduct");
	}
}
