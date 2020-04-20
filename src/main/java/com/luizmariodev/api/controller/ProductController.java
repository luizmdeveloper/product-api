package com.luizmariodev.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.domain.model.Product;
import com.luizmariodev.domain.repository.ProductRepository;
import com.luizmariodev.domain.service.ProductService;

@RestController	
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@GetMapping("{productId}")
	public ResponseEntity<Product> findOne(@PathVariable Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		
		if (optionalProduct.isPresent())
			return ResponseEntity.ok(optionalProduct.get());
		
		return ResponseEntity.notFound().build();
	}
		
	@PostMapping
	public Product save(@RequestBody @Valid Product product) {
		return productService.save(product);
	}
	
	@PutMapping("{productId}")
	public Product save(@PathVariable Long productId, @RequestBody @Valid Product product) {
		return productService.update(productId, product);
	}
	
	@DeleteMapping("{productId}") 
	public void delete(@PathVariable Long productId) {
		productService.delete(productId);
	}


}
