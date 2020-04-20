package com.luizmariodev.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmariodev.domain.exception.EntityNotFoundException;
import com.luizmariodev.domain.model.Category;
import com.luizmariodev.domain.model.Product;
import com.luizmariodev.domain.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Transactional
	public Product save(Product product) {
		Category categorySave = findCategoryById(product.getCategory().getId());
		product.setCategory(categorySave);		
		return productRepository.save(product);
	}
	
	@Transactional
	public Product update(Long productId, @Valid Product product) {
		Product productSave = findBydId(productId);

		Category categorySave = findCategoryById(product.getCategory().getId());
		product.setCategory(categorySave);	
		BeanUtils.copyProperties(product, productSave, "id");
		
		return productRepository.save(productSave);
	}
	
	public Category findCategoryById(Long categoryId) {
		return categoryService.findById(categoryId);
	}
	
	private Product findBydId(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		
		if (!optionalProduct.isPresent()) 
			throw new EntityNotFoundException("Product not found");
		
		return optionalProduct.get();
	}

	@Transactional
	public void delete(Long productId) {
		try {		
			productRepository.deleteById(productId);
			productRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Product not found");
		}
	}

}
