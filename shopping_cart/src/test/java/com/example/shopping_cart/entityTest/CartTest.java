package com.example.shopping_cart.entityTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class CartTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal totalprice;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id",unique = true)
//	User user = new User();
	
	@OneToMany
	private List<ProductTest> products =new ArrayList<>();

	public CartTest(Long id, BigDecimal totalprice) {
		super();
		this.id = id;
		this.totalprice = totalprice;
	}

	public CartTest() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public List<ProductTest> getProducts() {
		return products;
	}

	public void setProducts(List<ProductTest> list) {
		this.products = list;
		
	}
	
}

