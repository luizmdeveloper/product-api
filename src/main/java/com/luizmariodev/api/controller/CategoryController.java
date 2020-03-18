package com.luizmariodev.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.luizmariodev.domain.model.Category;
import com.luizmariodev.domain.repository.CategoryRepository;
import com.luizmariodev.domain.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categorySerivce;
	
	@GetMapping
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("{categoryId}")
	public ResponseEntity<Category>  findBydId(@PathVariable Long categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		
		if (optionalCategory.isPresent())
			return ResponseEntity.ok(optionalCategory.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public Category save(@RequestBody Category category) {
		return categorySerivce.save(category);
	}
	
	@PutMapping("{categoryId}")
	public Category update(@PathVariable Long categoryId, @RequestBody Category category) {		
		return categorySerivce.update(categoryId, category);
	}
	
	@DeleteMapping("{categoryId}")
	public void delete(@PathVariable Long categoryId) {
		categorySerivce.delete(categoryId);
	} 	
}
