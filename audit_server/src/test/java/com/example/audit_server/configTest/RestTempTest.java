package com.example.audit_server.configTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = RestTempTest.class)
public class RestTempTest {

	@MockBean
	private RestTemplate restTemplate;

	@InjectMocks
	private RestTempTest restTempTest;

	@Test
	public void testRestTemplateBean() {
		// Arrange
		MockitoAnnotations.openMocks(this);

		// Act
		RestTemplate restTemplateBean = restTempTest.RestTemplate();

		// Assert
		assertThat(restTemplateBean).isNotNull();
	}

	private RestTemplate RestTemplate() {
		// TODO Auto-generated method stub
		return this.restTemplate;
	}
}
