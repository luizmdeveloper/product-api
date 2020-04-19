package com.luizmariodev.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.domain.model.Product;
import com.luizmariodev.domain.repository.ProductRepository;

@RestController	
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@GetMapping("{productId}")
	public Product findOne(@PathVariable Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId); 
		return optionalProduct.get();
	}
	

}
